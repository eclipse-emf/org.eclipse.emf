package pommod;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Developer;
import org.apache.maven.model.License;
import org.apache.maven.model.Model;
import org.apache.maven.model.Scm;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.eclipse.jdt.annotation.NonNull;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class POMEnhancer {

	private File dir;
	private boolean dryRun;

	public static void main(String[] args) {
		POMEnhancer instance = new POMEnhancer();
		
		CmdLineParser parser = new CmdLineParser(instance);
		try {
			parser.parseArgument(args);
			instance.run();
		} catch (CmdLineException | IOException e) {
			// handling of wrong arguments
			System.err.println(e.getMessage());
			parser.printUsage(System.err);
		}

	}
	
	@Option(name = "-dir", usage = "Sets the base directory to scan for POM files", required = true)
	public void setDir(@NonNull File dir) {
		this.dir = dir;
	}
	
	@Option(name = "-dryRun", usage = "When set files are not modified and result is dumped to sysout")
	public void setDryRun(boolean dryRun) {
		this.dryRun  = dryRun;
	}


	protected void run() throws IOException {
		System.out.println("Making POMs compliant for Maven Central.");
		System.out.println("Processing *.pom files in "+dir.getAbsolutePath() + "...");
		Files.find(dir.toPath(), 20, (path, basicFileAttributes) -> path.toFile().getPath().endsWith(".pom"))
			.forEach(path -> enhancePOMFile(path));
		System.out.println("Done.");
	}

	private void enhancePOMFile(Path path) {
		Model model = load(path.toFile());
		boolean modified = false;
		if (model.getName() == null) {
			model.setName(model.getArtifactId());
			modified = true;
		}
		if (model.getUrl() == null) {
			model.setUrl("https://www.eclipse.org/emf");
			modified = true;
		}
		if (model.getLicenses().isEmpty()) {
			License license = new License();
			license.setName("The Eclipse Public License Version 2.0");
			license.setUrl("https://www.eclipse.org/legal/epl-v20.html");
			license.setDistribution("repo");
			model.addLicense(license);
			modified = true;
		}
		if (model.getScm() == null) {
			Scm scm = new Scm();
			scm.setUrl("https://git.eclipse.org/c/emf/org.eclipse.emf.git/");
			scm.setConnection("git://git.eclipse.org/gitroot/emf/org.eclipse.emf.git/");
			model.setScm(scm);
			modified = true;
		}
		if (model.getDevelopers().isEmpty()) {
			Developer developer = new Developer();
			developer.setId("eclipse");
			developer.setName("Eclipse.org");
			developer.setEmail("info@eclipse.org");
			model.addDeveloper(developer);
			modified = true;
		}
		// fix ANTLR Runtime Dependency, see Bug#536882
		// version must be strictly 3.2.0 for Xtext
		for (Dependency dependency : model.getDependencies()) {
			if ("antlr-runtime".equals(dependency.getArtifactId())) {
				dependency.setVersion("[3.2.0, 3.2.1)");
				modified = true;
			}
		}
		
		if (modified) {
			save(model, path.toFile());
			System.out.println("  modified: "+path);
		}
	}
	
	protected Model load(File file) {
		MavenXpp3Reader reader = new MavenXpp3Reader();
		try {
			return reader.read(new FileReader(file));
		} catch (IOException | XmlPullParserException e) {
			throw new RuntimeException(e);
		}
	}

	protected void save(Model model, File target) {
		MavenXpp3Writer writer = new MavenXpp3Writer();
		try {
			if (dryRun) {
				writer.write(System.out, model);
			} else {
				writer.write(new FileWriter(target), model);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

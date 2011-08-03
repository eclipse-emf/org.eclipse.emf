package org.eclipse.emf.ecore.xcore.tests.util;

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.mwe.utils.StandaloneSetup;
import org.eclipse.xtext.diagnostics.ExceptionDiagnostic;
import org.eclipse.xtext.junit.serializer.SerializerTester;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

@RunWith(LabelledParameterized.class)
public abstract class AbstractModelSanityTest
{

	public static class FilePredicate implements Predicate<File>
	{
		private final String[] fileExts;

		public FilePredicate(String... fileExts)
		{
			this.fileExts = fileExts;
		}

		public boolean apply(File input)
		{
			for (String ext : fileExts)
				if (input.getName().endsWith("." + ext))
					return true;
			return false;
		}
	}

	protected static List<File> files;

	protected static void collectFiles(File dir, List<File> result, Predicate<File> shouldLoad)
	{
		for (File child : dir.listFiles())
		{
			if (shouldLoad.apply(child))
				result.add(child);
			if (child.isDirectory())
				collectFiles(child, result, shouldLoad);
		}
	}

	public static List<File> collectFiles(String directory, Predicate<File> shouldCollect)
	{
		File dir = new File(directory);
		if (!dir.isDirectory())
			throw new RuntimeException("Directory not found: " + directory);
		List<File> result = Lists.newArrayList();
		collectFiles(new File(directory), result, shouldCollect);
		return result;
	}

	protected static List<File> collectFiles(String directory, String... fileExtensions)
	{
		return collectFiles(directory, new FilePredicate(fileExtensions));
	}

	protected static Collection<Object[]> collectTestFiles(String root, String... fileExts)
	{
		init();
		List<Object[]> result = Lists.newArrayList();
		ResourceSet rs = new XtextResourceSet();
		URI rootURI = URI.createFileURI(new File(root).getAbsolutePath());
		for (File file : collectFiles(root, fileExts))
		{
			URI uri = URI.createFileURI(file.getAbsolutePath());
			Resource res = null;
			try
			{
				res = rs.getResource(uri, true);
			} catch (Exception t)
			{
				if (res == null)
					res = rs.createResource(uri);
				res.getErrors().add(new ExceptionDiagnostic(t));
			}
			List<String> segmentsList = uri.deresolve(rootURI).segmentsList();
			String name = Joiner.on('/').join(segmentsList.subList(1, segmentsList.size()));
			result.add(new Object[]
			{ name, res });
		}
		return result;
	}

	private static void init()
	{
		new StandaloneSetup().setPlatformUri(".");
	}

	protected String label;

	protected XtextResource resource;

	public AbstractModelSanityTest(String label, Resource res) throws Exception
	{
		this.label = label;
		this.resource = (XtextResource) res;
	}

	protected String annotateDocumentWithIssues(String document, List<Issue> issues)
	{
		String[] lines = document.split("\n");
		Multimap<Integer, Issue> issueByLine = HashMultimap.create();
		for (Issue issue : issues)
			issueByLine.put(issue.getLineNumber(), issue);
		List<String> result = Lists.newArrayList();
		for (int i = 0; i < lines.length; i++)
		{
			Collection<Issue> lineIssues = issueByLine.get(i);
			if (lineIssues.isEmpty())
				result.add(lines[i]);
			else
			{
				StringBuilder line = new StringBuilder(lines[i]);
				for (Issue issue : lineIssues)
				{
					line.append("\n");
					line.append("// " + formatIssue(issue));
				}
				result.add(line.toString());
				issueByLine.removeAll(line);
			}
		}
		for (Issue issue : issues)
			if (issue.getLineNumber() >= lines.length)
				result.add("// " + formatIssue(issue));
		return Joiner.on('\n').join(result);
	}

	protected String formatIssue(Issue issue)
	{
		StringBuilder result = new StringBuilder();
		result.append(issue.getSeverity());
		result.append(": ");
		result.append(issue.getMessage());
		result.append(" (");
		result.append(issue.getCode());
		result.append(" )");
		return result.toString();
	}

	protected String getIssuesText(List<Issue> issues)
	{
		List<String> result = Lists.newArrayList();
		for (Issue issue : issues)
			result.add(formatIssue(issue));
		return Joiner.on('\n').join(result);
	}

	@Test
	public void serialize() throws Exception
	{
		Assert.assertTrue(resource.getContents().size() > 0);
		EObject root = resource.getContents().get(0);
		SerializerTester tester = resource.getResourceServiceProvider().get(SerializerTester.class);
		tester.assertSerializeWithNodeModel(root);
		tester.assertSerializeWithoutNodeModel(root);
	}

	@Test
	public void validate()
	{
		IResourceValidator validator = resource.getResourceServiceProvider().get(IResourceValidator.class);
		List<Issue> issues = validator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
		String expectedDocument = resource.getParseResult().getRootNode().getText();
		String actualDocument = annotateDocumentWithIssues(expectedDocument, issues);
		Assert.assertEquals(getIssuesText(issues), expectedDocument, actualDocument);
	}

}

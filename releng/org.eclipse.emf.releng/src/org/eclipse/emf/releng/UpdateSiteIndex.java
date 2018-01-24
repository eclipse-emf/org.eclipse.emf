package org.eclipse.emf.releng;

import java.util.*;
import java.io.*;
import org.eclipse.emf.releng.UpdateSiteIndexGenerator;

public class UpdateSiteIndex
{
  protected static String nl;
  public static synchronized UpdateSiteIndex create(String lineSeparator)
  {
    nl = lineSeparator;
    UpdateSiteIndex result = new UpdateSiteIndex();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">" + NL + "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">" + NL + "<head>" + NL + "  <meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\"/>" + NL + "  <title>";
  protected final String TEXT_2 = "</title>" + NL + "  <meta name=\"keywords\" content=\"eclipse,emf,emf update site\"/>" + NL + "  <meta name=\"description\" content=\"EMF's p2 Update Sites\"/>" + NL + "  <link href=\"//fonts.googleapis.com/css?family=Open+Sans:400,700,300,600,100\" rel=\"stylesheet\" type=\"text/css\"/>" + NL + "  <link rel=\"stylesheet\" href=\"https://www.eclipse.org/eclipse.org-common/themes/solstice/public/stylesheets/styles.min.css\"/>" + NL + "  <link rel=\"icon\" type=\"image/ico\" href=\"https://www.eclipse.org/eclipse.org-common/themes/solstice/public/images/favicon.ico\"/>" + NL + "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>" + NL + "</head>" + NL + "" + NL + "  <body id=\"body_solstice\">" + NL + "    <script>" + NL + "" + NL + "      function copyToClipboard(element) {" + NL + "        var $temp = $(\"<input>\");" + NL + "        $(\"body\").append($temp);" + NL + "        $temp.val($(element).text()).select();" + NL + "        document.execCommand(\"copy\");" + NL + "        $temp.remove();" + NL + "      }" + NL + "" + NL + "      function toggle(id) {" + NL + "        var e = document.getElementById(id);" + NL + "        if (e.title == 'Expand All') {" + NL + "          e.title= 'Collapse All';" + NL + "          e.innerHTML = '&#x25E2;';" + NL + "        } else {" + NL + "          e.title= 'Expand All';" + NL + "          e.innerHTML = '&#x25B7;';" + NL + "        }" + NL + "      }" + NL + "" + NL + "      function expand(id) {" + NL + "        var t = document.getElementById('all');" + NL + "        var e = document.getElementById(id);" + NL + "        var f = document.getElementById(id+\"_arrows\");" + NL + "        if (t.title == 'Collapse All'){" + NL + "          e.style.display = 'block';" + NL + "          f.innerHTML = '&#x25E2;';" + NL + "        } else {" + NL + "          e.style.display = 'none';" + NL + "          f.innerHTML = '&#x25B7;';" + NL + "        }" + NL + "      }" + NL + "" + NL + "      function expand_collapse(id) {" + NL + "        var e = document.getElementById(id);" + NL + "        var f = document.getElementById(id+\"_arrows\");" + NL + "        if (e.style.display == 'none'){" + NL + "          e.style.display = 'block';" + NL + "          f.innerHTML = '&#x25E2;';" + NL + "        } else {" + NL + "          e.style.display = 'none';" + NL + "          f.innerHTML = '&#x25B7;';" + NL + "        }" + NL + "      }" + NL + "" + NL + "    </script>" + NL + "" + NL + "    <header role=\"banner\" id=\"header-wrapper\">" + NL + "      <div class=\"container\">" + NL + "        <div class=\"row\" id=\"header-row\">" + NL + "          <div class=\"hidden-xs col-sm-8 col-md-6 col-lg-5\" id=\"header-left\">" + NL + "            <div class=\"wrapper-logo-default\">" + NL + "              <a href=\"https://www.eclipse.org/\">" + NL + "                <img class=\"logo-eclipse-default img-responsive hidden-xs\" alt=\"Eclipse Log\" src=\"https://www.eclipse.org/eclipse.org-common/themes/solstice/public/images/logo/eclipse-426x100.png\"/>" + NL + "              </a>" + NL + "            </div>" + NL + "          </div>" + NL + "        </div>" + NL + "      </div>" + NL + "    </header>" + NL + "" + NL + "    <section class=\"hidden-print default-breadcrumbs\" id=\"breadcrumb\">" + NL + "      <div class=\"container\">" + NL + "        <h3 class=\"sr-only\">Breadcrumbs</h3>" + NL + "        <div class=\"row\">" + NL + "          <div class=\"col-sm-16 padding-left-30\">" + NL + "            <ol class=\"breadcrumb\">" + NL + "              <li><a href=\"https://www.eclipse.org/\">Home</a></li>" + NL + "              <li><a href=\"https://www.eclipse.org/projects/\">Projects</a></li>" + NL + "              <li><a href=\"https://www.eclipse.org/modeling\">Modeling</a></li>" + NL + "              <li><a href=\"https://www.eclipse.org/modeling/emf\">EMF</a></li>";
  protected final String TEXT_3 = NL + "              <li class=\"active\">";
  protected final String TEXT_4 = "</li>";
  protected final String TEXT_5 = NL + "              <li><a href=\"";
  protected final String TEXT_6 = "\">";
  protected final String TEXT_7 = "</a></li>";
  protected final String TEXT_8 = NL + "            </ol>" + NL + "          </div>" + NL + "          <div class=\"col-sm-8 margin-top-15\"></div>" + NL + "        </div>" + NL + "      </div>" + NL + "    </section>" + NL + "" + NL + "    <main class=\"no-promo\">" + NL + "    <div class=\"novaContent container\" id=\"novaContent\">" + NL + "" + NL + "      <!-- navigation sidebar -->" + NL + "      <aside id=\"leftcol\" class=\"col-md-4\">" + NL + "        <ul id=\"leftnav\" class=\"ul-left-nav fa-ul hidden-print\">" + NL + "          <li class=\"separator\">" + NL + "           <a class=\"separator\" href=\"https://www.eclipse.org/modeling/emf\">Home</a>" + NL + "          </li>";
  protected final String TEXT_9 = NL + "          <li";
  protected final String TEXT_10 = " class=\"separator\"";
  protected final String TEXT_11 = ">";
  protected final String TEXT_12 = NL + "            <tt class=\"orange\">&#xbb;</tt>";
  protected final String TEXT_13 = NL + "            <a class=\"separator\" href=\"";
  protected final String TEXT_14 = "/index.html\">";
  protected final String TEXT_15 = "</a>";
  protected final String TEXT_16 = NL + "            <div style=\"float: right;\"><tt class=\"orange\">&#xab;</tt></div>";
  protected final String TEXT_17 = NL + "          </li>";
  protected final String TEXT_18 = NL + "        </ul>" + NL + "      </aside>" + NL + "" + NL + "      <div id=\"maincontent\">" + NL + "        <div id=\"midcolumn\">" + NL + "          <h2 style=\"text-align: center;\">";
  protected final String TEXT_19 = "</h2>";
  protected final String TEXT_20 = NL + "          <p style=\"font-size: 125%; text-align: center;\">" + NL + "            <b>Built: ";
  protected final String TEXT_21 = "</b>" + NL + "          </p>";
  protected final String TEXT_22 = NL + "          <p style=\"text-align: center;\">" + NL + "            <button title=\"Copy to Clipboard\" class=\"orange\" style=\"font-size: 150%; background-color: white; border: none; padding: 0px 0px;\" onclick=\"copyToClipboard('#p1')\">&#x270e;</button>" + NL + "            <span id=\"p1\" style=\"font-size: 125%\">";
  protected final String TEXT_23 = "</span>";
  protected final String TEXT_24 = NL + "            <br/>" + NL + "            <button title=\"Copy to Clipboard\" class=\"orange\" style=\"font-size: 125%; margin-left: 1em; background-color: white; border: none; padding: 0px 0px;\" onclick=\"copyToClipboard('#";
  protected final String TEXT_25 = "')\">&#x270e;</button>" + NL + "            <a href=\"";
  protected final String TEXT_26 = "/index.html\" id=\"";
  protected final String TEXT_27 = "\" style=\"font-size: 100%; font-weight: normal;\">";
  protected final String TEXT_28 = "</a>";
  protected final String TEXT_29 = NL + "          </p>";
  protected final String TEXT_30 = NL + "          <br/>" + NL + "          <img style=\"float:right\" src=\"https://www.eclipse.org/modeling/emf/images/emf_logo.png\" alt=\"\"/>";
  protected final String TEXT_31 = NL + "           <p>This is the root folder for the Eclipse Modeling Framework's p2 update sites.</p>" + NL + "           <p>These update sites are produced by <a href=\"https://ci.eclipse.org/emf/\" target=\"emf_jipp\">https://ci.eclipse.org/emf/</a>.</p>" + NL + "           <p>EMF provides a large number of fine-grained features that are useful for provisioning a minimal target platform and for building smaller RCP applications and IDE products.</p>";
  protected final String TEXT_32 = NL + "           <p>" + NL + "             This is a p2 update site for the Eclipse Modeling Framework." + NL + "             Use the <button title=\"Copy to Clipboard\" class=\"orange\" style=\"background-color: white; border: none; padding: 0px 0px;\" onclick=\"copyToClipboard('#p1')\">&#x270e;</button> glyph to copy the update site URL to the system clipboard.";
  protected final String TEXT_33 = NL + "             Alternatively, you can download the archive of this repository from the download sidebar on the right.";
  protected final String TEXT_34 = NL + "           </p>";
  protected final String TEXT_35 = NL + "           <p>" + NL + "             This composite update site references the most recent successful nightly build." + NL + "           </p>";
  protected final String TEXT_36 = NL + "           <p>" + NL + "             This composite update site references the ";
  protected final String TEXT_37 = " most recent successful nightly builds." + NL + "           </p>";
  protected final String TEXT_38 = NL + "           <p>" + NL + "             This simple update site will be retained <b>only</b> until it is no longer one of the ";
  protected final String TEXT_39 = " most recent successful nightly builds." + NL + "           </p>";
  protected final String TEXT_40 = NL + "           <p>" + NL + "             This composite update site references the most recent successful milestone build." + NL + "           </p>";
  protected final String TEXT_41 = NL + "           <p>" + NL + "             This composite update site references ";
  protected final String TEXT_42 = " successful milestone builds." + NL + "             These sites will be retained <b>only</b> until the first successful milestone build of the next EMF release." + NL + "           </p>";
  protected final String TEXT_43 = NL + "           <p>" + NL + "             This simple update site will be retained <b>only</b> until the first successful milestone build of the next EMF release." + NL + "           </p>";
  protected final String TEXT_44 = NL + "           <p>" + NL + "             This composite update site references the most recent release of EMF." + NL + "           </p>";
  protected final String TEXT_45 = NL + "           <p>" + NL + "             This composite update site references ";
  protected final String TEXT_46 = " releases." + NL + "             These sites will be retained <b>permanently</b>." + NL + "           </p>";
  protected final String TEXT_47 = NL + "           <p>" + NL + "             This simple update site will be retained <b>permanently</b>." + NL + "           </p>";
  protected final String TEXT_48 = NL + "           <p>This update site provides access to the following coarse-grained SDK features, useful for installing in a development IDE," + NL + "              as well as coarse-grained Target Component features, useful provisioning a complete target platform:</p>";
  protected final String TEXT_49 = NL + "           <p>This update site provides access to the following fine-grained features:</p>";
  protected final String TEXT_50 = NL + "           <ul>";
  protected final String TEXT_51 = NL + "             <li>";
  protected final String TEXT_52 = "</li>";
  protected final String TEXT_53 = NL + "           </ul>";
  protected final String TEXT_54 = NL + "           <p>" + NL + "             The update sites listed below provide access to specific versions of the features listed above.";
  protected final String TEXT_55 = NL + "             Use the <button title=\"Copy to Clipboard\" class=\"orange\" style=\"background-color: white; border: none; padding: 0px 0px;\">&#x270e;</button> glyph to copy the update site URL to the system clipboard.";
  protected final String TEXT_56 = NL + "           </p>";
  protected final String TEXT_57 = NL + "           <h3><a href=\"";
  protected final String TEXT_58 = "\" style=\"font-weight: normal;\">";
  protected final String TEXT_59 = "</a></h3>" + NL + "           <p>The following is the p2 update site URL for this repository:</p>" + NL + "           <p style=\"margin-left: 1em\">" + NL + "             <button title=\"Copy to Clipboard\" class=\"orange\" style=\"font-size: 125%; background-color: white; border: none; padding: 0px 0px;\" onclick=\"copyToClipboard('#";
  protected final String TEXT_60 = "')\">&#x270e;</button>" + NL + "             <a href=\"";
  protected final String TEXT_61 = "\" id=\"";
  protected final String TEXT_62 = "\" style=\"font-weight: normal;\">";
  protected final String TEXT_63 = "</a>" + NL + "           </p>" + NL + "           <p>Click the header link or the update site link for more details.</p>";
  protected final String TEXT_64 = NL + "          <ul style=\"margin-left: -1em\">";
  protected final String TEXT_65 = "<b>";
  protected final String TEXT_66 = "<li style=\"font-size: 90%;\">";
  protected final String TEXT_67 = "</li>";
  protected final String TEXT_68 = "</b>";
  protected final String TEXT_69 = NL + "           </ul>";
  protected final String TEXT_70 = NL + "           <h3>EMF Bundles</h3>" + NL + "           <p>" + NL + "             This update site provides access to the bundles listed below." + NL + "             Each section is expandable via";
  protected final String TEXT_71 = NL + "             <span style=\"white-space: nowrap;\">the&nbsp;<button id=\"all\" title=\"Expand All\" class=\"orange\" style=\"background-color: white; border: none; padding: 0px 0px;\" onclick=\"toggle('all');";
  protected final String TEXT_72 = "\">&#x25B7;</button>&nbsp;glyph</span>";
  protected final String TEXT_73 = NL + "             to provide detailed information about the exports and imports of each bundle." + NL + "           </p>" + NL + "           <ul>" + NL + "             <li>" + NL + "               The &#x21D6; glyph denotes the exported bundle identifier along with its full version." + NL + "             </li>" + NL + "             <li>" + NL + "               The &#x2196; glyph denotes an exported java package along with its full version, if specified." + NL + "             </li>" + NL + "             <li>" + NL + "               The &#x21D8; glyph denotes a required bundle along with its version range, if specified, as well as information about whether the requirement is optional and if so, whether it's greedy." + NL + "             </li>" + NL + "             <li>" + NL + "               The &#x2198; glyph denotes a required package along with its version range, if specified." + NL + "             </li>" + NL + "           </ul>" + NL + "" + NL + "          <ul style=\"margin-left: -1em; list-style-type: none; padding: 0; margin: 0;\">";
  protected final String TEXT_74 = NL + "            <li style=\"font-size: 90%;\">" + NL + "              <button id=\"";
  protected final String TEXT_75 = "_arrows\" class=\"orange\" style=\"background-color: white; margin-left: 1em; border: none; padding: 0px 0px;\" onclick=\"expand_collapse('";
  protected final String TEXT_76 = "');\">&#x25B7;</button>" + NL + "              <span style=\"font-size:100%;\">";
  protected final String TEXT_77 = "</span>" + NL + "              <div id=\"";
  protected final String TEXT_78 = "\" style=\"display:none; margin-left: 2em;\">";
  protected final String TEXT_79 = NL + "                ";
  protected final String TEXT_80 = "<br/>";
  protected final String TEXT_81 = NL + "              </div>" + NL + "            </li>";
  protected final String TEXT_82 = NL + "           </ul>";
  protected final String TEXT_83 = NL + NL + "        </div>" + NL;
  protected final String TEXT_84 = NL + "        <div style=\"padding-top: 100;\" id=\"rightcolumn\">" + NL + "          <div class=\"sideitem\">" + NL + "            <h6>Downloads</h6>" + NL + "            <p>" + NL + "              <a style=\"font-size: 90%;\" href=\"";
  protected final String TEXT_85 = "\" target=\"_blank\">";
  protected final String TEXT_86 = "</a>" + NL + "              <br/>" + NL + "              <a class=\"btn btn-warning\" style=\"margin-top: 5pt; font-size: 90%;\" href=\"";
  protected final String TEXT_87 = "\" target=\"_blank\" title=\"Download ";
  protected final String TEXT_88 = "\">Download</a>" + NL + "              <a class=\"btn btn-warning\" style=\"margin-top: 5pt; border-color: DarkGreen; background-color: OliveDrab; font-size: 90%; padding-left: 0.2em; padding-right: 0.2em;\" href=\"";
  protected final String TEXT_89 = "\" target=\"_blank\" title=\"Download ";
  protected final String TEXT_90 = "\">sha256</a>" + NL + "              <a class=\"btn btn-warning\" style=\"margin-top: 5pt; border-color: DarkGreen; background-color: OliveDrab; font-size: 90%; padding-left: 0.2em; padding-right: 0.2em;\" href=\"";
  protected final String TEXT_91 = "\" target=\"_blank\" title=\"Download ";
  protected final String TEXT_92 = "\">sha512</a>" + NL + "            </p>" + NL + "          </div>";
  protected final String TEXT_93 = NL + "          <div class=\"sideitem\">" + NL + "            <h6>Git Commits</h6>";
  protected final String TEXT_94 = NL + "            <p>" + NL + "              <button title=\"Copy Commit ID ";
  protected final String TEXT_95 = " to Clipboard\" class=\"orange\" style=\"font-size: 150%; background-color: transparent; border: none; padding: 0px 0px;\" onclick=\"copyToClipboard('#";
  protected final String TEXT_96 = "_commit_id')\">&#x270e;</button>" + NL + "              <a style=\"font-size: 100%;\" href=\"";
  protected final String TEXT_97 = "\" target=\"";
  protected final String TEXT_98 = "_git\">";
  protected final String TEXT_99 = "</a>" + NL + "              <br/>" + NL + "              <tt><a id=\"";
  protected final String TEXT_100 = "_commit_id\" class=\"btn btn-warning\" style=\"text-transform: none; margin-top: 5pt; border-color: DarkGreen; background-color: OliveDrab; font-size: 70%; padding-top: 0.2ex; padding-bottom: 0.2ex; padding-left: 0.2em; padding-right: 0.2em;\" href=\"";
  protected final String TEXT_101 = "\" target=\"";
  protected final String TEXT_102 = "_git_commit\" title=\"";
  protected final String TEXT_103 = " Git Commit ID\">";
  protected final String TEXT_104 = "</a></tt>" + NL + "            </p>";
  protected final String TEXT_105 = NL + "          </div>";
  protected final String TEXT_106 = NL + "        </div>" + NL;
  protected final String TEXT_107 = NL + "      </div>" + NL + "     </div>" + NL + "     </main>" + NL + "  </body>" + NL + "</html>";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    UpdateSiteIndexGenerator parent = (UpdateSiteIndexGenerator)argument;
    List<UpdateSiteIndexGenerator> children = parent.getChildren();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(parent.getTitle());
    stringBuffer.append(TEXT_2);
    for (Map.Entry<String, String> entry : parent.getBreadcrumbs().entrySet()) {
    if (entry.getValue() == null) {
    stringBuffer.append(TEXT_3);
    stringBuffer.append(entry.getKey());
    stringBuffer.append(TEXT_4);
    } else {
    stringBuffer.append(TEXT_5);
    stringBuffer.append(entry.getValue());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(entry.getKey());
    stringBuffer.append(TEXT_7);
    }
    }
    stringBuffer.append(TEXT_8);
    for (Map.Entry<String, String> entry : parent.getNavigation().entrySet()) { String label = entry.getValue(); boolean isTopLevel = !label.startsWith("-"); if (!isTopLevel) label = label.substring(1); int index = label.indexOf('@'); if (index != -1) label = label.substring(0, label.length() -1);
    stringBuffer.append(TEXT_9);
    if (isTopLevel) {
    stringBuffer.append(TEXT_10);
    }
    stringBuffer.append(TEXT_11);
    if (!isTopLevel) {
    stringBuffer.append(TEXT_12);
    }
    stringBuffer.append(TEXT_13);
    stringBuffer.append(entry.getKey());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_15);
    if (index != -1) {
    stringBuffer.append(TEXT_16);
    }
    stringBuffer.append(TEXT_17);
    }
    stringBuffer.append(TEXT_18);
    stringBuffer.append(parent.getTitle());
    stringBuffer.append(TEXT_19);
    if (children.isEmpty()) {String date = parent.getDate(); if (date != null) {
    stringBuffer.append(TEXT_20);
    stringBuffer.append(date);
    stringBuffer.append(TEXT_21);
    }}
    if (!parent.isRoot()) {
    stringBuffer.append(TEXT_22);
    stringBuffer.append(parent.getSiteURL());
    stringBuffer.append(TEXT_23);
    Map<String, String> repositories = parent.getRepositoryChildren(); if (repositories != null) { for (Map.Entry<String, String> entry : repositories.entrySet()) {String repository = entry.getKey(); String relativeURL = entry.getValue(); String id = parent.getFolderID(repository);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(relativeURL);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(repository);
    stringBuffer.append(TEXT_28);
    }}
    stringBuffer.append(TEXT_29);
    }
    stringBuffer.append(TEXT_30);
    if (parent.isRoot()) {
    stringBuffer.append(TEXT_31);
    } else {
    stringBuffer.append(TEXT_32);
    if (parent.hasArchive()) {
    stringBuffer.append(TEXT_33);
    }
    stringBuffer.append(TEXT_34);
    {String buildType = parent.getBuildType(); boolean isLatest = parent.isLatest(); Map<String, String> repositoryChildren = parent.getRepositoryChildren(); if ("nightly".equals(buildType)) {
    if (isLatest) {
    stringBuffer.append(TEXT_35);
    } else if (repositoryChildren != null) {
    stringBuffer.append(TEXT_36);
    stringBuffer.append(parent.getRepositoryChildren().size());
    stringBuffer.append(TEXT_37);
    } else {
    stringBuffer.append(TEXT_38);
    stringBuffer.append(UpdateSiteGenerator.RETAINED_NIGHTLY_BUILDS);
    stringBuffer.append(TEXT_39);
    }
    } else if ("milestone".equals(buildType)) {
    if (isLatest) {
    stringBuffer.append(TEXT_40);
    } else if (repositoryChildren != null) {
    stringBuffer.append(TEXT_41);
    stringBuffer.append(parent.getRepositoryChildren().size());
    stringBuffer.append(TEXT_42);
    } else {
    stringBuffer.append(TEXT_43);
    }
    } else {
    if (isLatest) {
    stringBuffer.append(TEXT_44);
    } else if (repositoryChildren != null) {
    stringBuffer.append(TEXT_45);
    stringBuffer.append(parent.getRepositoryChildren().size());
    stringBuffer.append(TEXT_46);
    } else {
    stringBuffer.append(TEXT_47);
    }
    }}
    if (!children.isEmpty()) {
    stringBuffer.append(TEXT_48);
    } else {
    stringBuffer.append(TEXT_49);
    }
    }
    if (!children.isEmpty()) {
    List<String> sdks = parent.getSDKs(); if (!sdks.isEmpty()) {
    stringBuffer.append(TEXT_50);
    for (String sdk: sdks) {
    stringBuffer.append(TEXT_51);
    stringBuffer.append(sdk);
    stringBuffer.append(TEXT_52);
    }
    stringBuffer.append(TEXT_53);
    }
    stringBuffer.append(TEXT_54);
    if (parent.isRoot()) {
    stringBuffer.append(TEXT_55);
    }
    stringBuffer.append(TEXT_56);
    for (UpdateSiteIndexGenerator child : children) { String id = parent.getFolderID(child.getFolderName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(child.getRelativeIndexURL());
    stringBuffer.append(TEXT_58);
    stringBuffer.append(child.getTitle());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(child.getRelativeIndexURL());
    stringBuffer.append(TEXT_61);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(child.getSiteURL());
    stringBuffer.append(TEXT_63);
    }
    } else {
    List<String> features = parent.getFeatures(); if (!features.isEmpty()) {
    stringBuffer.append(TEXT_64);
    for (String feature: features) {
    if (parent.isSDK(feature)) {
    stringBuffer.append(TEXT_65);
    }
    stringBuffer.append(TEXT_66);
    stringBuffer.append(feature.replace(" ", "&nbsp;"));
    stringBuffer.append(TEXT_67);
    if (parent.isSDK(feature)) {
    stringBuffer.append(TEXT_68);
    }
    }
    stringBuffer.append(TEXT_69);
    }
    Map<String, List<String>> bundles = parent.getBundles(); if (!bundles.isEmpty()) { 
    stringBuffer.append(TEXT_70);
    { StringBuilder onClick = new StringBuilder(); for (String bundle : bundles.keySet()) {  onClick.append("expand('").append(parent.getFolderID(bundle)).append("');"); }
    stringBuffer.append(TEXT_71);
    stringBuffer.append(onClick);
    stringBuffer.append(TEXT_72);
    }
    stringBuffer.append(TEXT_73);
    for (Map.Entry<String, List<String>> entry: bundles.entrySet()) { String bundle = entry.getKey(); String id = parent.getFolderID(bundle);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(bundle.replace(" ", "&nbsp;"));
    stringBuffer.append(TEXT_77);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_78);
    for (String line : entry.getValue()) {
    stringBuffer.append(TEXT_79);
    stringBuffer.append(line);
    stringBuffer.append(TEXT_80);
    }
    stringBuffer.append(TEXT_81);
    }
    stringBuffer.append(TEXT_82);
    }
    }
    stringBuffer.append(TEXT_83);
    if (parent.hasArchive()) { String archiveName = new File(parent.getArchive()).getName(); String sha256Name = new File(parent.getDigest("SHA-256")).getName(); String sha512Name = new File(parent.getDigest("SHA-512")).getName();
    stringBuffer.append(TEXT_84);
    stringBuffer.append(parent.getArchiveDownloadURL());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(archiveName);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(parent.getArchiveDownloadURL());
    stringBuffer.append(TEXT_87);
    stringBuffer.append(archiveName);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(sha256Name);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(sha256Name);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(sha512Name);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(sha512Name);
    stringBuffer.append(TEXT_92);
    { Map<String, String> commits = parent.getCommits(); if (!commits.isEmpty()) {
    stringBuffer.append(TEXT_93);
    for (Map.Entry<String, String> entry : commits.entrySet()) { String label = entry.getKey(); String url = entry.getValue(); String gitRepoURL = url.substring(0, url.indexOf("commit")) + "log/";String id = url.substring(url.indexOf('=') + 1);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(label.toLowerCase());
    stringBuffer.append(TEXT_96);
    stringBuffer.append(gitRepoURL);
    stringBuffer.append(TEXT_97);
    stringBuffer.append(label.toLowerCase());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(label.toLowerCase());
    stringBuffer.append(TEXT_100);
    stringBuffer.append(url);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(label.toLowerCase());
    stringBuffer.append(TEXT_102);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(id);
    stringBuffer.append(TEXT_104);
    }
    stringBuffer.append(TEXT_105);
    }}
    stringBuffer.append(TEXT_106);
    }
    stringBuffer.append(TEXT_107);
    return stringBuffer.toString();
  }
}

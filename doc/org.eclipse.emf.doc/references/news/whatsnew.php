<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<?php $pre = "../"; 
		$HTMLTitle = "Eclipse Tools - EMF - What's New";
		$ProjectName = array(
			"EMF Documents",
			'Eclipse Modeling Framework Documents',
			'What\'s New',
			$pre."images/reference.gif"
			);
		include $pre."includes/header.php"; ?>
<html><head><title></title><meta http-equiv=Content-Type content="text/html; charset=ISO-8859-1"></head>
<body lang="EN-US">
<?php 

	// parse out the anchor links to gen a TOC
	if (!$ver) { $ver="2.0.0"; }

	if (is_file("whatsnew".$ver.".html"	)) {
		$f = file("whatsnew".$ver.".html"	); 
	} else {
		$f = array();
	}
	$anchors = array();
	$currentAnchor = "";
	$bugs=0;
	foreach ($f as $line) {
		if (strstr($line,"What's new in")) {
			preg_match("/\<a name\=\"([a-z]+\_(\d+)([a-z]*))\"\>(What\'s new in (.+)\?{0,1})\<\/a\>/",$line,$m);
			if (!$anchors[$m[2]]) { $anchors[$m[2]] = array(); }
			$anchors[$m[2]][$m[3]] = array($m[1],$m[4]); // link, label
		} else if (strstr($line,"<a name=\"")) {
			//echo $line;
			preg_match("/\<a name\=\"([a-z]+\_(\d+)([a-z]*))\"\>(.+)\<\/a\>/",$line,$m);
			if (!$anchors[$m[2]]) { $anchors[$m[2]] = array(); }
			$anchors[$m[2]][$m[3]] = array($m[1],$m[4]); // link, label
			$currentAnchor = array($m[2],$m[3]); // store nesting
		} else if (strstr($line,"<a href=\"http://bugs.eclipse.org/bugs/show_bug.cgi?id=")) {
			//echo $line;
			preg_match("/\<a href\=\"(http\:\/\/bugs\.eclipse\.org\/bugs\/show\_bug\.cgi\?id\=\d+)\"\>(\d+)\<\/a\>/",$line,$m);
			$anchors[$currentAnchor[0]][$currentAnchor[1].$bugs] = array($m[1],"".$m[2]); // link, label
			$bugs++;
		}

	} 

	if (sizeof($f)>0) {
		// Generated: Table Of Contents
		$w = "<hr noshade size=1>";
		$w .= "<table summary="" border=0 width=\"100%\">";
			$w .= '<tr><td colspan="5"><a name="toc"><b>Table Of Contents</b></a><a name=\"toc\">&nbsp;</a></td>'.
					"<td align=right><a class=\"metaLink\" href=\"#top\">top</a>&nbsp;|&nbsp;<a class=\"metaLink\" href=\"#toc\">toc</a></td>".
					'</tr>'."\n";
			//$w .= '<tr><td>&nbsp;&nbsp;</td><td colspan=\"3\">&#149; <a href="#top">Overview</a></td></tr>'."\n";

		$bugStarted = false;
		foreach ($anchors as $super => $anchor2) { // 200 -> a,b,c,d -> (href, label)
			foreach ($anchor2 as $sub => $anchor3) { // a -> (href, label)
				if ($anchor3[0]) { 
					if (strstr($anchor3[0],"bug")) {
						$lev = 3;
					} else if ($sub) { 
						$lev = 2;
					} else { 
						$lev = 1;
					}
					if ($lev==3) { 
						if (!$bugStarted) { 
							for ($i=0;$i<$lev;$i++) { 
								$w .= "<td>&nbsp;&nbsp;&nbsp;</td>";
							}
							$w .= "<td align=right><small>Bugzilla:</small></td><td><table border=0><tr>"; 
						}
						$bugStarted=true;
						$w .= '<td colspan="'.'1'.'"><small><a target="bugzilla" href="'.$anchor3[0].'">'.$anchor3[1].'</a></small></td>'."\n";
					} else {
						if ($bugStarted) { $w .= "</tr></table></td></tr>\n"; }  // close nested table
						$bugStarted=false;
						$w .= '<tr>';
						for ($i=0;$i<$lev;$i++) { 
							$w .= "<td>&nbsp;&nbsp;&nbsp;</td>";
						}
						$w .= '<td colspan="'.(5-$lev).'"><a href="#'.$anchor3[0].'">'.$anchor3[1].'</a></td></tr>'."\n";
					}
				}
			}
		}
		if ($bugStarted) { $w .= "</tr></table></td>"; $bugStarted = false; } // close nested table

		$w .= "<tr><td>&nbsp;</td></tr></table>";
		echo $w;
	}

	foreach ($f as $line) {
		echo $line; 
	} 
?>

<table summary=""><tr><td>&nbsp;&nbsp;</td><td>
<a name="relnotes1" class="category">Other Release Notes:</a> 
<?php $files = loadDirSimple("../news","whatsnew(.*)\.html","f");
	rsort($files); reset($files);
	$i=0;
	foreach ($files as $file) { 
		if (!strstr($file,$ver)) {
			preg_match("/whatsnew(.*)\.html/",$file,$m);
			$vver = $m[1];
			if ($i>0) { echo ', '; }
			echo '<a class="subcategory" href="'.$pre.'news/whatsnew.php?ver='.$vver.'">'.$vver.'</a>';
			$i++;
		}
	}
	?></td></tr></table>

<?php include $pre."includes/footer.php"; ?>

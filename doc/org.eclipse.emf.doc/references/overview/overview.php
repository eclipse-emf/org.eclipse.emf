<?php
	$pre = "../../";
	if (is_file($pre."docs/overview/".$doc.".html")) { 
		$f = file($pre."docs/overview/".$doc.".html");
	} else { 
		$f = array();
		echo "$doc document not found!";
	}

	foreach ($f as $line) { 
		if (strstr($line,"quicknav")) {
			$line = preg_replace("/(\<a href\=\"\#quicknav\"\>)(\<\/a\>)/","$1Quick Nav$2",$line);
		}
		echo $line;
	}

   include $pre."includes/scripts.php";
   include $pre."includes/footer.php";
?>
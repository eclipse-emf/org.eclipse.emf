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

<table BORDER=0 CELLPADDING=2 WIDTH="100%" summary="">
<tr>
<td>&nbsp;&nbsp;&nbsp; </td>
<td>
<?php getNews(-1,"all"); ?>
</td>
</tr>
</table>
<?php include $pre."includes/footer.php"; ?>


<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" />

	<!-- parameters defining prefixes -->
	<xsl:param name="prefix-separator" />
	<xsl:param name="prefix-qualifier-separator" />
	<xsl:param name="container-prefix" />
	<xsl:param name="classpath-entry-prefix" />
	<!-- full path of the file containing the replacement data -->
	<xsl:param name="container-replacement-data-file" />

	<!-- constants -->
	<xsl:variable name="classpath-entry-kind-container" select="'con'" />
	<xsl:variable name="classpath-entry-kind-library" select="'lib'" />

	<!-- variables -->
	<xsl:variable name="container-replacement-data" select="document($container-replacement-data-file)/properties/property" />
	<xsl:variable name="complete-container-prefix" select="concat($container-prefix, $prefix-separator)" />
	<xsl:variable name="containers-to-replace"
		select="($container-replacement-data[@name = concat($complete-container-prefix, @value)])/@value" />

	<!-- identity transform idiom -->
	<xsl:template match="node() | @*">
		<xsl:copy>
			<xsl:apply-templates select="node() | @*" />
		</xsl:copy>
	</xsl:template>

	<!-- replace the container classpath entries for which there is replacement data -->
	<xsl:template match="/classpath/classpathentry[@kind = $classpath-entry-kind-container and @path = $containers-to-replace]">
		<xsl:variable name="complete-classpath-entry-prefix" select="concat($classpath-entry-prefix, $prefix-qualifier-separator, @path, $prefix-separator)" />

		<xsl:for-each select="($container-replacement-data[@name = concat($complete-classpath-entry-prefix, @value)])/@value">
			<classpathentry kind="{$classpath-entry-kind-library}" path="{.}" />
		</xsl:for-each>
	</xsl:template>
</xsl:transform>

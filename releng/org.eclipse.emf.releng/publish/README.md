# How to build?

Run `mvn`.

The pom.xml is configured to execute the default goals `clean resources:resources verify`.

# pommod

The directory `pommod` contains a small Maven based project that contains a small utility to
enrich the POM files produced by the CBI aggregator by mandatory information for upload to Maven Central.


# OSSRH

* Deploy snapshot artifacts into repository https://oss.sonatype.org/content/repositories/snapshots
* Deploy release artifacts into the staging repository https://oss.sonatype.org/service/local/staging/deploy/maven2
* Promote staged artifacts into repository 'Releases'
* Download snapshot and release artifacts from group https://oss.sonatype.org/content/groups/public
* Download snapshot, release and staged artifacts from staging group https://oss.sonatype.org/content/groups/staging

# Q & A

## Why is there a feature.xml?

This project must be of a type recognized by Tycho. It contains an empty feature.xml and is declared as packaging-type 'eclipse-feature' for that sake.

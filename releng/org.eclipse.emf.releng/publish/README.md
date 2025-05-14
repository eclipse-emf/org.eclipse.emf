# How to build?

# OSSRH

* Deploy snapshot artifacts into repository https://oss.sonatype.org/content/repositories/snapshots
* Deploy release artifacts into the staging repository https://oss.sonatype.org/service/local/staging/deploy/maven2
* Promote staged artifacts into repository 'Releases'
* Download snapshot and release artifacts from group https://oss.sonatype.org/content/groups/public
* Download snapshot, release and staged artifacts from staging group https://oss.sonatype.org/content/groups/staging

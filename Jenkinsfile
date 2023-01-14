pipeline {
  agent {
    label 'centos-latest'
  }

  options {
    buildDiscarder(logRotator(numToKeepStr: '10'))
    disableConcurrentBuilds()
    // skipDefaultCheckout true
  }

  tools {
    maven 'apache-maven-latest'
    jdk 'temurin-jdk11-latest'
  }

  environment {
    PUBLISH_LOCATION = 'builds'
    BUILD_TIMESTAMP = sh(returnStdout: true, script: 'date +%Y%m%d%H%M').trim()
    CHECKOUT = 'false'
  }

  parameters {
    choice(
      name: 'BUILD_TYPE',
      choices: ['nightly', 'milestone', 'release'],
      description: '''
        Choose the type of build.
        Note that a release build will not promote the build, but rather will promote the most recent milestone build.
        '''
    )

    choice(
      name: 'TARGET_PLATFORM',
      choices: [
        '2023-03',
        '2022-12',
        '2022-09',
        '2022-06',
        '2022-03',
        '2021-12',
        '2021-09',
        '2021-06',
        '2021-03',
        '2020-12',
        '2020-09',
        '2020-06',
        '2020-03',
        '2019-12',
        '2019-09',
        '2019-06',
        '2019-03',
        '2018-12',
        '2018-09',
        'photon',
        'oxygen',
        'neon',
        'mars',
        'luna',
        'kepler'
      ],
      description: '''
        Choose the named target platform against which to compile and test.
        This is relevant only for nightly and milestone builds.
        '''
    )

    choice(
      name: 'JAVA_VERSION',
      choices: ['11', '1.8'],
      description: '''
        Choose the Java version for the execution environment of the Tycho build.
        '''
    )

    booleanParam(
      name: 'ECLIPSE_SIGN',
      defaultValue: false,
      description: '''
        Choose whether or not the bundles will be signed.
        This is relevant only for nightly and milestone builds.
      '''
    )
 
    booleanParam(
      name: 'PROMOTE',
      defaultValue: false,
      description: 'Whether to promote the build to the download server.'
    )

    booleanParam(
      name: 'ARCHIVE',
      defaultValue: false,
      description: 'Whether to archive the workspace.'
    )
  }

  stages {
    stage('Display Parameters') {
      steps {
        echo "BUILD_TIMESTAMP=${env.BUILD_TIMESTAMP}"
        echo "BUILD_TYPE=${params.BUILD_TYPE}"
        echo "JAVA_VERSION=${params.JAVA_VERSION}"
        echo "TARGET_PLATFORM=${params.TARGET_PLATFORM}"
        echo "ECLIPSE_SIGN=${params.ECLIPSE_SIGN}"
        echo "PROMOTE=${params.PROMOTE}"
        echo "ARCHIVE=${params.ARCHIVE}"
        script {
          env.BUILD_TYPE = params.BUILD_TYPE
          env.JAVA_VERSION = params.JAVA_VERSION
          env.TARGET_PLATFORM = params.TARGET_PLATFORM
          env.ECLIPSE_SIGN = params.ECLIPSE_SIGN
          env.PROMOTE = params.PROMOTE && env.ECLIPSE_SIGN
        }
      }
    }

    stage('Git Checkout') {
      when {
        environment name: 'CHECKOUT', value: 'true'
      }
      steps {
        script {
          def gitVariables = checkout(
            poll: false,
            scm: [
              $class: 'GitSCM',
              branches: [[name: '*' + '/master']],
              doGenerateSubmoduleConfigurations: false,
              // extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'emf']],
              submoduleCfg: [],
              userRemoteConfigs: [[url: 'https://git.eclipse.org/r/emf/org.eclipse.emf.git']]
              // userRemoteConfigs: [[url: 'https://github.com/eclipse-emf/emf.git']]
              // userRemoteConfigs: [[url: 'https://github.com/merks/emf-xsd.git']]
            ]
          )

          echo "$gitVariables"
          env.GIT_COMMIT = gitVariables.GIT_COMMIT
        }
      }
    }

    stage('Build') {
      steps {
        sshagent(['projects-storage.eclipse.org-bot-ssh']) {
          dir('.') {
            sh '''
              if [[ $PROMOTE == false ]]; then
                promotion_argument='-Dpromote=false -Dorg.eclipse.justj.p2.manager.args='
              fi
              mvn  \
                --fail-at-end \
                --no-transfer-progress \
                $promotion_argument \
                -Dmaven.artifact.threads=16 \
                -Dbuild.id=${BUILD_TIMESTAMP} \
                -Dcommit.id=$GIT_COMMIT \
                -DECLIPSE_SIGN=${ECLIPSE_SIGN} \
                -Dtarget-platform=${TARGET_PLATFORM} \
                -DjavaVersion=${JAVA_VERSION} \
                -Dbuild.type=$BUILD_TYPE \
                -Dorg.eclipse.justj.p2.manager.build.url=$JOB_URL \
                -Dorg.eclipse.justj.p2.manager.relative=$PUBLISH_LOCATION \
                clean \
                verify
              '''
          }
        }
      }
    }

    stage('Archive Results') {
      when {
        expression {
          params.ARCHIVE
        }
      }
      steps {
        archiveArtifacts '**'
      }
    }
  }

  post {
    failure {
      mail to: 'ed.merks@gmail.com',
      subject: "[EMF CI] Build Failure ${currentBuild.fullDisplayName}",
      mimeType: 'text/html',
      body: "Project: ${env.JOB_NAME}<br/>Build Number: ${env.BUILD_NUMBER}<br/>Build URL: ${env.BUILD_URL}<br/>Console: ${env.BUILD_URL}/console"
      archiveArtifacts allowEmptyArchive: true, artifacts: '**'
    }

    fixed {
      mail to: 'ed.merks@gmail.com',
      subject: "[EMF CI] Back to normal ${currentBuild.fullDisplayName}",
      mimeType: 'text/html',
      body: "Project: ${env.JOB_NAME}<br/>Build Number: ${env.BUILD_NUMBER}<br/>Build URL: ${env.BUILD_URL}<br/>Console: ${env.BUILD_URL}/console"
    }

    always {
      junit '**/target/surefire-reports/TEST-*.xml'
    }

    cleanup {
      deleteDir()
    }
  }
}
node('linux')
{
  stage ('Poll') {
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/caddyport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/caddyport.git'), string(name: 'PORT_DESCRIPTION', value: 'Fast and extensible multi-platform HTTP/1-2-3 web server with automatic HTTPS' ), string(name: 'BUILD_LINE', value: 'STABLE'), string(name: 'NODE_LABEL', value: "go_122" ) ]
  }
}

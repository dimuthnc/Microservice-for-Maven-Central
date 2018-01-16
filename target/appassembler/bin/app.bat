@REM ----------------------------------------------------------------------------
@REM  Copyright 2001-2006 The Apache Software Foundation.
@REM
@REM  Licensed under the Apache License, Version 2.0 (the "License");
@REM  you may not use this file except in compliance with the License.
@REM  You may obtain a copy of the License at
@REM
@REM       http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM  Unless required by applicable law or agreed to in writing, software
@REM  distributed under the License is distributed on an "AS IS" BASIS,
@REM  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM  See the License for the specific language governing permissions and
@REM  limitations under the License.
@REM ----------------------------------------------------------------------------
@REM
@REM   Copyright (c) 2001-2006 The Apache Software Foundation.  All rights
@REM   reserved.

@echo off

set ERROR_CODE=0

:init
@REM Decide how to startup depending on the version of windows

@REM -- Win98ME
if NOT "%OS%"=="Windows_NT" goto Win9xArg

@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" @setlocal

@REM -- 4NT shell
if "%eval[2+2]" == "4" goto 4NTArgs

@REM -- Regular WinNT shell
set CMD_LINE_ARGS=%*
goto WinNTGetScriptDir

@REM The 4NT Shell from jp software
:4NTArgs
set CMD_LINE_ARGS=%$
goto WinNTGetScriptDir

:Win9xArg
@REM Slurp the command line arguments.  This loop allows for an unlimited number
@REM of arguments (up to the command line limit, anyway).
set CMD_LINE_ARGS=
:Win9xApp
if %1a==a goto Win9xGetScriptDir
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto Win9xApp

:Win9xGetScriptDir
set SAVEDIR=%CD%
%0\
cd %0\..\.. 
set BASEDIR=%CD%
cd %SAVEDIR%
set SAVE_DIR=
goto repoSetup

:WinNTGetScriptDir
set BASEDIR=%~dp0\..

:repoSetup
set REPO=


if "%JAVACMD%"=="" set JAVACMD=java

if "%REPO%"=="" set REPO=%BASEDIR%\repo

set CLASSPATH="%BASEDIR%"\config;"%REPO%"\org\json\json\20170516\json-20170516.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-databind\2.9.2\jackson-databind-2.9.2.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-annotations\2.9.0\jackson-annotations-2.9.0.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-core\2.9.2\jackson-core-2.9.2.jar;"%REPO%"\org\apache\maven\maven-plugin-api\3.0\maven-plugin-api-3.0.jar;"%REPO%"\org\apache\maven\maven-model\3.0\maven-model-3.0.jar;"%REPO%"\org\apache\maven\maven-artifact\3.0\maven-artifact-3.0.jar;"%REPO%"\org\sonatype\sisu\sisu-inject-plexus\1.4.2\sisu-inject-plexus-1.4.2.jar;"%REPO%"\org\sonatype\sisu\sisu-inject-bean\1.4.2\sisu-inject-bean-1.4.2.jar;"%REPO%"\org\sonatype\sisu\sisu-guice\2.1.7\sisu-guice-2.1.7-noaop.jar;"%REPO%"\org\eclipse\aether\aether-api\1.0.0.v20140518\aether-api-1.0.0.v20140518.jar;"%REPO%"\org\eclipse\aether\aether-spi\1.0.0.v20140518\aether-spi-1.0.0.v20140518.jar;"%REPO%"\org\eclipse\aether\aether-util\1.0.0.v20140518\aether-util-1.0.0.v20140518.jar;"%REPO%"\org\eclipse\aether\aether-impl\1.0.0.v20140518\aether-impl-1.0.0.v20140518.jar;"%REPO%"\org\eclipse\aether\aether-connector-basic\1.0.0.v20140518\aether-connector-basic-1.0.0.v20140518.jar;"%REPO%"\org\eclipse\aether\aether-transport-file\1.0.0.v20140518\aether-transport-file-1.0.0.v20140518.jar;"%REPO%"\org\eclipse\aether\aether-transport-http\1.0.0.v20140518\aether-transport-http-1.0.0.v20140518.jar;"%REPO%"\org\apache\httpcomponents\httpclient\4.2.6\httpclient-4.2.6.jar;"%REPO%"\org\apache\httpcomponents\httpcore\4.2.5\httpcore-4.2.5.jar;"%REPO%"\commons-codec\commons-codec\1.6\commons-codec-1.6.jar;"%REPO%"\org\slf4j\jcl-over-slf4j\1.6.2\jcl-over-slf4j-1.6.2.jar;"%REPO%"\org\apache\maven\maven-aether-provider\3.1.0\maven-aether-provider-3.1.0.jar;"%REPO%"\org\apache\maven\maven-model-builder\3.1.0\maven-model-builder-3.1.0.jar;"%REPO%"\org\codehaus\plexus\plexus-interpolation\1.16\plexus-interpolation-1.16.jar;"%REPO%"\org\apache\maven\maven-repository-metadata\3.1.0\maven-repository-metadata-3.1.0.jar;"%REPO%"\org\codehaus\plexus\plexus-component-annotations\1.5.5\plexus-component-annotations-1.5.5.jar;"%REPO%"\org\codehaus\plexus\plexus-utils\3.0.10\plexus-utils-3.0.10.jar;"%REPO%"\com\google\guava\guava\18.0\guava-18.0.jar;"%REPO%"\org\eclipse\sisu\org.eclipse.sisu.plexus\0.1.1\org.eclipse.sisu.plexus-0.1.1.jar;"%REPO%"\org\eclipse\sisu\org.eclipse.sisu.inject\0.1.1\org.eclipse.sisu.inject-0.1.1.jar;"%REPO%"\org\codehaus\plexus\plexus-classworlds\2.4\plexus-classworlds-2.4.jar;"%REPO%"\org\sonatype\sisu\sisu-guice\3.1.6\sisu-guice-3.1.6-no_aop.jar;"%REPO%"\javax\inject\javax.inject\1\javax.inject-1.jar;"%REPO%"\org\yaml\snakeyaml\1.17\snakeyaml-1.17.jar;"%REPO%"\com\esotericsoftware\yamlbeans\yamlbeans\1.06\yamlbeans-1.06.jar;"%REPO%"\org\wso2\msf4j\msf4j-core\2.4.0\msf4j-core-2.4.0.jar;"%REPO%"\org\wso2\carbon\messaging\org.wso2.carbon.messaging\3.0.1\org.wso2.carbon.messaging-3.0.1.jar;"%REPO%"\org\wso2\carbon\org.wso2.carbon.core\5.2.0\org.wso2.carbon.core-5.2.0.jar;"%REPO%"\org\wso2\carbon\transport\org.wso2.carbon.transport.http.netty\5.0.1\org.wso2.carbon.transport.http.netty-5.0.1.jar;"%REPO%"\io\netty\netty-common\4.1.7.Final\netty-common-4.1.7.Final.jar;"%REPO%"\io\netty\netty-buffer\4.1.7.Final\netty-buffer-4.1.7.Final.jar;"%REPO%"\io\netty\netty-transport\4.1.7.Final\netty-transport-4.1.7.Final.jar;"%REPO%"\io\netty\netty-handler\4.1.7.Final\netty-handler-4.1.7.Final.jar;"%REPO%"\io\netty\netty-codec\4.1.7.Final\netty-codec-4.1.7.Final.jar;"%REPO%"\io\netty\netty-codec-http\4.1.7.Final\netty-codec-http-4.1.7.Final.jar;"%REPO%"\io\netty\netty-codec-http2\4.1.7.Final\netty-codec-http2-4.1.7.Final.jar;"%REPO%"\io\netty\netty-resolver\4.1.7.Final\netty-resolver-4.1.7.Final.jar;"%REPO%"\commons-pool\wso2\commons-pool\1.5.6.wso2v1\commons-pool-1.5.6.wso2v1.jar;"%REPO%"\org\wso2\orbit\com\lmax\disruptor\3.3.2.wso2v2\disruptor-3.3.2.wso2v2.jar;"%REPO%"\org\wso2\orbit\org\yaml\snakeyaml\1.16.0.wso2v1\snakeyaml-1.16.0.wso2v1.jar;"%REPO%"\javax\websocket\javax.websocket-api\1.1\javax.websocket-api-1.1.jar;"%REPO%"\javax\ws\rs\javax.ws.rs-api\2.0\javax.ws.rs-api-2.0.jar;"%REPO%"\org\wso2\msf4j\jaxrs-delegates\2.4.0\jaxrs-delegates-2.4.0.jar;"%REPO%"\org\slf4j\slf4j-api\1.7.5\slf4j-api-1.7.5.jar;"%REPO%"\org\slf4j\slf4j-log4j12\1.6.0\slf4j-log4j12-1.6.0.jar;"%REPO%"\log4j\log4j\1.2.14\log4j-1.2.14.jar;"%REPO%"\com\google\code\gson\gson\2.2.4\gson-2.2.4.jar;"%REPO%"\org\apache\servicemix\bundles\org.apache.servicemix.bundles.commons-beanutils\1.8.3_2\org.apache.servicemix.bundles.commons-beanutils-1.8.3_2.jar;"%REPO%"\commons-logging\commons-logging\1.1.1\commons-logging-1.1.1.jar;"%REPO%"\org\wso2\carbon\config\org.wso2.carbon.config\2.1.2\org.wso2.carbon.config-2.1.2.jar;"%REPO%"\org\wso2\carbon\secvault\org.wso2.carbon.secvault\5.0.8\org.wso2.carbon.secvault-5.0.8.jar;"%REPO%"\org\wso2\carbon\utils\org.wso2.carbon.utils\2.0.2\org.wso2.carbon.utils-2.0.2.jar;"%REPO%"\commons-io\wso2\commons-io\2.4.0.wso2v1\commons-io-2.4.0.wso2v1.jar;"%REPO%"\org\wso2\aethermicroservice\aether-microservice\1.0.0\aether-microservice-1.0.0.jar

set ENDORSED_DIR=
if NOT "%ENDORSED_DIR%" == "" set CLASSPATH="%BASEDIR%"\%ENDORSED_DIR%\*;%CLASSPATH%

if NOT "%CLASSPATH_PREFIX%" == "" set CLASSPATH=%CLASSPATH_PREFIX%;%CLASSPATH%

@REM Reaching here means variables are defined and arguments have been captured
:endInit

%JAVACMD% %JAVA_OPTS%  -classpath %CLASSPATH% -Dapp.name="app" -Dapp.repo="%REPO%" -Dapp.home="%BASEDIR%" -Dbasedir="%BASEDIR%" org.service.Application_Deploy_MavenAether %CMD_LINE_ARGS%
if %ERRORLEVEL% NEQ 0 goto error
goto end

:error
if "%OS%"=="Windows_NT" @endlocal
set ERROR_CODE=%ERRORLEVEL%

:end
@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" goto endNT

@REM For old DOS remove the set variables from ENV - we assume they were not set
@REM before we started - at least we don't leave any baggage around
set CMD_LINE_ARGS=
goto postExec

:endNT
@REM If error code is set to 1 then the endlocal was done already in :error.
if %ERROR_CODE% EQU 0 @endlocal


:postExec

if "%FORCE_EXIT_ON_ERROR%" == "on" (
  if %ERROR_CODE% NEQ 0 exit %ERROR_CODE%
)

exit /B %ERROR_CODE%

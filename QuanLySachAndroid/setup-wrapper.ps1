$ErrorActionPreference = 'Stop'
$root = Split-Path -Parent $MyInvocation.MyCommand.Path
$wrapperDir = Join-Path $root 'gradle\wrapper'
New-Item -ItemType Directory -Force -Path $wrapperDir | Out-Null
$baseUrl = 'https://raw.githubusercontent.com/gradle/gradle/v8.11.1'
Write-Host 'Dang tai cac tep Gradle Wrapper chinh thuc...'
Invoke-WebRequest -Uri "$baseUrl/gradle/wrapper/gradle-wrapper.jar" -OutFile (Join-Path $wrapperDir 'gradle-wrapper.jar')
Invoke-WebRequest -Uri "$baseUrl/gradlew.bat" -OutFile (Join-Path $root 'gradlew.bat')
Invoke-WebRequest -Uri "$baseUrl/gradlew" -OutFile (Join-Path $root 'gradlew')
Write-Host 'Hoan tat. Mo thu muc project nay bang Android Studio.' -ForegroundColor Green

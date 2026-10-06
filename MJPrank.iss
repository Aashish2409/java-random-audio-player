[Setup]
AppName=MJPrank
AppVersion=1.0
DefaultDirName={autopf}\MJPrank
DefaultGroupName=MJPrank
OutputDir=installer
OutputBaseFilename=MJPrankSetup
Compression=lzma
SolidCompression=yes
WizardStyle=modern

[Files]
Source: "MJPrank\*"; DestDir: "{app}"; Flags: recursesubdirs createallsubdirs
Source: "sounds\*"; DestDir: "{app}\sounds"; Flags: recursesubdirs createallsubdirs

[Icons]
Name: "{group}\MJPrank"; Filename: "{app}\MJPrank.exe"

[Run]
Filename: "{app}\MJPrank.exe"; Description: "Start MJPrank"; Flags: nowait postinstall skipifsilent
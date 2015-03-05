@echo off 
net start OracleOraDb11g_home1TNSListener
net start OracleServiceORCL
if %errorlevel% == 2 echo Could not start service.
if %errorlevel% == 0 echo Service started successfully.
echo Errorlevel: %errorlevel%
exit
@echo off
chcp 65001 > nul
echo ========================================================
echo       BIEN DICH TOAN BO 9 BAI TAP RMI - LAB 06
echo ========================================================

javac -encoding UTF-8 bai1_hello/*.java bai2_calculator/*.java bai3_prime/*.java bai4_contacts/*.java bai5_chat/*.java bai6_bank/*.java bai7_geometry/*.java bai8_flight/*.java bai9_auction/*.java

if %ERRORLEVEL% EQU 0 (
    echo [THANH CONG] Toan bo 9 bai tap da duoc bien dich xong!
) else (
    echo [THAT BAI] Co loi trong qua trinh bien dich!
)
pause

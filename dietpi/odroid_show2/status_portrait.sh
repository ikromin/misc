#!/bin/bash

# Updates the status of the DietPi OS on the odroid-show2
# based on https://github.com/Fourdee/DietPi/blob/master/dietpi/dietpi-cloudshell
# ANSI codes from: http://odroid.com/dokuwiki/doku.php?id=en:show_using&s[]=ansi

TTY_V2="/dev/ttyUSB0"
TMP_FILE="/tmp/status"
COLOR_HEADING="[32m"
COLOR_TEXT="[37m"

function tty_echo2 {
	printf "%s" "$1" >> $TMP_FILE
}

function tty_echo {
	printf "%s\n" "$1" >> $TMP_FILE
}

function tty_cmd {
	echo -ne "\e$1" >> $TMP_FILE
}

# reset tmp file
rm -f $TMP_FILE

# init TTY
stty -F $TTY_V2 500000
sleep 0.2

# reset screen
tty_cmd [2J
tty_cmd [40m
tty_cmd [2s
tty_cmd [0r

HW_MODEL=$(sed -n 1p /DietPi/dietpi/.hw_model)

# cpu frequency (average frequency)
CPU_FREQ_1=$(( $(cat /sys/devices/system/cpu/cpu[0-3]/cpufreq/scaling_cur_freq|awk '{s+=$1} END {print s}') / 4000 ))
CPU_FREQ_2="N/A"
if (( $HW_MODEL == 11 )); then
	CPU_FREQ_2=$(( $(cat /sys/devices/system/cpu/cpu[4-7]/cpufreq/scaling_cur_freq|awk '{s+=$1} END {print s}') / 4000 ))
fi

# host name
tty_cmd $COLOR_HEADING
tty_echo "`hostname`"
tty_cmd $COLOR_TEXT
tty_echo "`/sbin/ifconfig -a|grep \"inet addr\"|grep -v \"127.0.0.1\"|awk -F: '{print $2}'|cut -d\  -f1`"
tty_echo

tty_cmd $COLOR_HEADING
tty_echo "CPU Information"
tty_cmd $COLOR_TEXT

# cpu temperature
CPU_TEMP=$(/DietPi/dietpi/dietpi-cpuinfo 1)

if [[ $CPU_TEMP =~ ^-?[0-9]+$ ]]; then

	#Obtain colour for temps
	if (( $CPU_TEMP >= 75 )); then
		C_CPUTEMP="[31m"
	elif (( $CPU_TEMP >= 60 )); then
		C_CPUTEMP="[33m"
	elif (( $CPU_TEMP >= 45 )); then
		C_CPUTEMP="[33m"
	else
		C_CPUTEMP="[36m"
	fi
fi

tty_cmd $C_CPUTEMP
tty_echo2 "${CPU_TEMP}C"
tty_cmd [37m

# cpu usage
tty_echo " `top -bn 2 -d 0.01 | grep '^%Cpu' | tail -n 1 | awk '{print $2+$4+$6}'`% "
tty_echo "${CPU_FREQ_1}|${CPU_FREQ_2} Mhz"
tty_echo

# disks
tty_cmd $COLOR_HEADING
tty_echo "Disk Usage"
tty_cmd $COLOR_TEXT
tty_echo "`df -h -x tmpfs|sort -r|grep /dev/|awk '{printf \"%-4s %-4s %s\n\", $5, $2, $1}'|sed -e \"s|/dev/||\"`"
tty_echo

# time and uptime
tty_cmd $COLOR_HEADING
tty_echo "`date +\"%d-%b-%Y   %R\"`"
tty_cmd $COLOR_TEXT
tty_echo "`uptime -p`"

# send output to odroid-show2
while read in; do echo -ne "$in\n" > $TTY_V2; sleep 0.1; done < $TMP_FILE


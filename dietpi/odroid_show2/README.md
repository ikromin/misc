# DietPi Odroid-Show2 System Status

This script displays a summary of some system stats on the Odroid-Show2 TFT board. Unlike the dietpi-cloudshell script, this script summarises everything in one place but is less configrable.

Odroid-Show2 can be found here: [ODROID-SHOW2](http://www.hardkernel.com/main/products/prdt_info.php?g_code=G141743018597)

This script uses ANSI commands described [here](http://odroid.com/dokuwiki/doku.php?id=en:show_using&s[]=ansi) to update the screen. There are more examples available [here](http://odroid.com/dokuwiki/doku.php?id=en:show_examples).

Here's a photo of the script in action

![](https://github.com/ikromin/misc/blob/master/dietpi/odroid_show2/photo.jpg)

Below is a sample output of the script -

```
PulseDroid
192.168.1.109

CPU Information
95C 76.9% @ 1400|2000 Mhz

Disk Usage
3%   of 230G  sdc1
86%  of 1.9T  sdb2
35%  of 3.6T  sda1
7%   of 29G   mmcblk0p2
41%  of 71M   mmcblk0p1

09-Sep-2016   01:22
up 3 hours, 56 minutes
```

Note that this has only been tested with DietPi on Odroid-XU4.

The script writes its data to */tmp/status* before it is sent to Odroid-Show2.

This script should be added to your crontab to run every minute. This is not intended for real-time use.
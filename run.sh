#!/bin/sh
[ -z "$DISPLAY" ] && DISPLAY=:10
export DISPLAY
xdpyinfo -display $DISPLAY >/dev/null 2>&1 || nohup Xvfb  +extension RANDR $DISPLAY -screen 0 1280x1024x24 -ac &
CHROME=/usr/bin/chromium
[ -x "/usr/bin/chromium-browser" ] && CHROME=/usr/bin/chromium-browser
nohup $CHROME --remote-debugging-port=9222 --dns-prefetch-disable --disable-gpu &
ps ax | grep -v grep | grep selenium-server || nohup java -jar ./selenium-server-standalone-2.53.1.jar &
sleep 2

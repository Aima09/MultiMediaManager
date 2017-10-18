/**
 * - dm_notify.js -
 * A Light jQuery notification plugin. For websites and mobile apps.
 *
 * author: Dionisis Mastavralis
 * date: July 28, 2015
 * version: 0.1.0
 * copyright - GNU GPL
 */

/* Shows a notification or warning messages on the screen.
 * Params:
 *  text: The text to show
 	time: The time for the notification to auto close.
 *  color: The color of the notification. red, green, blue, orange, yellow or custom (Based on Bootstrap library)
 *  
*/

//The notification function
function dm_notification(text, color, time, icon) {
    var icon_span = "",
        html_element,
        time = time,
        $cont = $('#dm-notif');

	if(time){
		time = time;	
	}else{
		time = 5000;	
	}

    if (icon) {
        icon_span = "<span class='" + icon + "'></span> ";
    }

    // Create the HTML element
    html_element = $('<div class="dm-notification dm-notify-' + color + '">' + icon_span + text + '</div>').fadeIn('fast');

    // Append the label to the cont
    $cont.append(html_element);

    // Remove the notification on click
    html_element.on('click', function() {
        dm_notification_close($(this));
    });

    // After time seconds, the notification fades out
    setTimeout(function() {
        dm_notification_close($cont.children('.dm-notification').first());
    }, time);
}

function dm_notification_close(elem) {
    // if you don't pass an argument the function removes all alerts
	
    if (typeof elem !== "undefined") {
        elem.fadeOut('fast', function() {
            $(this).remove();
        });
    } else {
        $('.alert').fadeOut('fast', function() {
            $(this).remove();
        });
    }
}

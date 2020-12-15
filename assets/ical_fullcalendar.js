// Depends on ./ical_events.js

recur_events = []

function an_filter(string) {
    // remove non alphanumeric chars
    return string.replace(/[^\w\s]/gi, '')
}

function moment_icaltime(moment, timezone) {
    return new ICAL.Time().fromJSDate(moment.toDate())
}

function expand_recur_events(start, end, timezone, events_callback) {
    events = []
    recur_events.forEach(function(event, i){
        event_properties = event.event_properties
        expand_recur_event(event, moment_icaltime(start, timezone), moment_icaltime(end, timezone), function(event){
            fc_event(event, function(event){
                events.push(merge_events(event_properties, merge_events({className:['recur-event']}, event)))
            })
        })
    })
    events_callback(events)
}

function fc_events(ics, event_properties) {
    events = []
    ical_events(
        ics,
        function(event){
            fc_event(event, function(event){
                events.push(merge_events(event_properties, event))
            })
        },
        function(event){
            event.event_properties = event_properties
            recur_events.push(event)
        }
    )
    return events
}

function merge_events(e, f) {
    // f has priority
    for (k in e) {
        if (k == 'className') {
            f[k] = [].concat(f[k]).concat(e[k])
        } else if (! f[k]) {
            f[k] = e[k]
        }
    }
    return f
}

function fc_event(event, event_callback) {
    console.log(event);
    const objectArray = event.jCal[1];
    const organizers = objectArray[7];
    var location = objectArray[8];
    const locationNext = objectArray[9];
    if( location[0] === "location" ){
        location = location[3];
    } else {
        location = locationNext[3];
    }
    //console.log(organizers[1]['cn']);
    e = {
        title:event.getFirstPropertyValue('summary')+" \n"+ location +" - "+organizers[1]['cn'],
        url:event.getFirstPropertyValue('url'),
        id:event.getFirstPropertyValue('uid'),
        className:['event-'+an_filter(event.getFirstPropertyValue('uid'))],
        allDay:false
    }
    try {
        e['start'] = event.getFirstPropertyValue('dtstart').toJSDate()
    } catch (TypeError) {
        console.debug('Undefined "dtstart", vevent skipped.')
        return
    }
    try {
        e['end'] = event.getFirstPropertyValue('dtend').toJSDate()
    } catch (TypeError) {
        e['allDay'] = true
    }
    event_callback(e)
}

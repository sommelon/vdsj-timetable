ics_sources = [
    {url:'mycalendar.ics',event_properties:{color:''}},
]


function data_req (url, callback) {
    req = new XMLHttpRequest()
    req.addEventListener('load', callback)
    req.open('GET', url)
    req.send()
}

function add_recur_events() {
    if (sources_to_load_cnt < 1) {
        $('#calendar').fullCalendar('addEventSource', expand_recur_events)
    } else {
        setTimeout(add_recur_events, 30)
    }
}

function load_ics(ics){
    data_req(ics.url, function(){
        $('#calendar').fullCalendar('addEventSource', fc_events(this.response, ics.event_properties))

        sources_to_load_cnt -= 1
    })
}

$(document).ready(function() {
    $('#calendar').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        buttonText: {
            today:    'Dnes',
            month:    'Mesiac',
            week:     'Týždeň',
            day:      'Deň',
            list:     'List'
        },
        lang: 'sk',
        firstDay: 1,
        defaultView: 'agendaWeek',
        defaultDate: '2020-12-15',
        eventClick: function(event) {
            // opens events in a popup window
            $('#popup').html(event.title);
            $('#popup').show();
            return false;
        },
    })
    sources_to_load_cnt = ics_sources.length
    ics_sources.forEach(function(ics){
        load_ics(ics)
    })
    add_recur_events()
})

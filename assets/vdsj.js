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
        locale: 'sk',
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        defaultView: 'agendaWeek',
        defaultDate: '2020-12-15'
    })
    sources_to_load_cnt = ics_sources.length
    ics_sources.forEach(function(ics){
        load_ics(ics)
    })
    add_recur_events()
})

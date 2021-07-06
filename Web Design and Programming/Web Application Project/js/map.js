var map;

var mapCenter = new google.maps.LatLng(0, 0);

function initMap() {
    var canberraMap = {
        center: mapCenter,
        zoom: 2,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    map = new google.maps.Map(document.getElementById("googlemap"), canberraMap);

    addGoogle(map);
    addIBM(map);
    addAmazon(map);
    addMicrosoft(map);
    addOracle(map);
    addSAS(map);
}

google.maps.event.addDomListener(window, 'load', initMap);

function addGoogle(map) {
    var googleplex = new google.maps.LatLng(37.429771, -122.085088);

    var markerGoogle = new google.maps.Marker({
        position: googleplex,
    });

    markerGoogle.setMap(map);

    var contentGoogle =
        '<h1>Googleplex</h1><img src="../WDPAssignment1/images/googleplex.jpg" style="float: left; width: 25%; margin-right: 10px;" /><p>The <b>Googleplex</b> is the corporate headquarters complex of Google and its parent company Alphabet Inc. It is located at 1600 Amphitheatre Parkway in Mountain View, California, United States.<br><a href="https://en.wikipedia.org/wiki/Googleplex">Wikipedia</a></p>';

    var infoGoogle = new google.maps.InfoWindow({
        content: contentGoogle
    });

    google.maps.event.addListener(markerGoogle, 'click',
        function () {
            infoGoogle.open(map, markerGoogle);
        });
}

function addIBM(map) {
    var chq = new google.maps.LatLng(41.109197, -73.718886);

    var markerIBM = new google.maps.Marker({
        position: chq,
    });

    markerIBM.setMap(map);

    var contentIBM =
        '<h1>CHQ</h1><img src="../WDPAssignment1/images/chq.jpg" style="float: left; width: 25%; margin-right: 10px;" /><p>IBM is headquartered in Armonk, New York, a community 37 miles (60 km) north of Midtown Manhattan.[61] Its principal building, referred to as CHQ, is a 283,000-square-foot (26,300 m2) glass and stone edifice on a 25-acre (10 ha) parcel amid a 432-acre former apple orchard the company purchased in the mid-1950s.<br><a href="https://en.wikipedia.org/wiki/IBM#Headquarters_and_offices">Wikipedia</a></p>';

    var infoIBM = new google.maps.InfoWindow({
        content: contentIBM
    });

    google.maps.event.addListener(markerIBM, 'click',
        function () {
            infoIBM.open(map, markerIBM);
        });
}

function addAmazon(map) {
    var doppler = new google.maps.LatLng(47.615272, -122.338369);

    var markerAmazon = new google.maps.Marker({
        position: doppler,
    });

    markerAmazon.setMap(map);

    var contentAmazon =
        '<h1>Doppler</h1><img src="../WDPAssignment1/images/doppler.jpg" style="float: left; width: 25%; margin-right: 10px;" /><p><b>Doppler</b>, also known as <b>Amazon Tower I</b>, and <b>Rufus 2.0 Block 14</b>, is a 524-foot-tall (160 m) office building in Seattle, Washington that houses the corporate headquarters of Amazon. It is part of the three-tower campus that Amazon is developing in the Denny Triangle neighborood, located at the intersection of Westlake Avenue and 7th Avenue near the Westlake Center and McGraw Square.<br><a href="https://en.wikipedia.org/wiki/Doppler_(building)">Wikipedia</a></p>';

    var infoAmazon = new google.maps.InfoWindow({
        content: contentAmazon
    });

    google.maps.event.addListener(markerAmazon, 'click',
        function () {
            infoAmazon.open(map, markerAmazon);
        });
}

function addMicrosoft(map) {
    var redmond = new google.maps.LatLng(47.642489, -122.136796);

    var markerMicrosoft = new google.maps.Marker({
        position: redmond,
    });

    markerMicrosoft.setMap(map);

    var contentMicrosoft =
        '<h1>Microsoft Redmond Campus</h1><img src="../WDPAssignment1/images/redmond.jpg" style="float: left; width: 25%; margin-right: 10px;" /><p>The <b>Microsoft Campus</b> is the informal name of Microsoft\'s corporate headquarters, located in Redmond, Washington, a suburb of Seattle. Microsoft initially moved onto the grounds of the campus on February 26, 1986, weeks before the company went public on March 13. The headquarters has experienced multiple expansions since its establishment. It is estimated to encompass over 8 million square feet (740,000 m^2) of office space and 30,000 to 40,000 employees.<br><a href="https://en.wikipedia.org/wiki/Microsoft_Redmond_campus">Wikipedia</a></p>';

    var infoMicrosoft = new google.maps.InfoWindow({
        content: contentMicrosoft
    });

    google.maps.event.addListener(markerMicrosoft, 'click',
        function () {
            infoMicrosoft.open(map, markerMicrosoft);
        });
}

function addOracle(map) {
    var oracleHQ = new google.maps.LatLng(37.528832, -122.264608);

    var markerOracle = new google.maps.Marker({
        position: oracleHQ,
    });

    markerOracle.setMap(map);

    var contentOracle =
        '<h1>Oracle Headquarters</h1><img src="../WDPAssignment1/images/oraclehq.jpg" style="float: left; width: 25%; margin-right: 10px;" /><p>Oracle Corporation has its overall headquarters on the San Francisco Peninsula in the Redwood Shores area of Redwood City, adjacent to Belmont and near San Carlos Airport (IATA airport code: SQL). Oracle HQ stands on the former site of Marine World Africa USA, which moved from Redwood Shores to Vallejo in 1986. Oracle Corporation originally leased two buildings on the site, moving its finance and administration departments from the corporation\'s former headquarters on Davis Drive, Belmont, California. Eventually, Oracle purchased the complex and constructed a further four main buildings.<br><a href="https://en.wikipedia.org/wiki/Oracle_Corporation#Offices">Wikipedia</a></p>';

    var infoOracle = new google.maps.InfoWindow({
        content: contentOracle
    });

    google.maps.event.addListener(markerOracle, 'click',
        function () {
            infoOracle.open(map, markerOracle);
        });
}

function addSAS(map) {
    var SASHQ = new google.maps.LatLng(35.828221, -78.764025);

    var markerSAS = new google.maps.Marker({
        position: SASHQ,
    });

    markerSAS.setMap(map);

    var contentSAS =
        '<h1>SAS Headquarters</h1><img src="../WDPAssignment1/images/sashq.jpg" style="float: left; width: 25%; margin-right: 10px;" /><p>SAS started building its current headquarters in a forested area of Cary, North Carolina in 1980. Later that year it started providing on-site daycare in order to keep an employee who was planning on being a stay-at-home mom. By 1984, SAS had begun building a fitness center, medical center, on-site cafe and other facilities. It had also developed some of its other benefits programs.SAS became known as a good place to work and was frequently recognized by national magazines like BusinessWeek, Working Mother and Fortune for its work environment.<br><a href="https://en.wikipedia.org/wiki/SAS_Institute#History">Wikipedia</a></p>';

    var infoSAS = new google.maps.InfoWindow({
        content: contentSAS
    });

    google.maps.event.addListener(markerSAS, 'click',
        function () {
            infoSAS.open(map, markerSAS);
        });
}
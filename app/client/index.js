
//==== DOM Ready =============================================================
$(document).ready(function() {

	// Populate the user table on initial page load
	console.log("Populating table...");
	populateTable();

	// Use other npm modules installed to fill the page:
	console.log("Using other npm modules installed to fill the page...");
	fillPageUsingNpmModules();

});

//==== Functions =============================================================

// Fill table with data using Jquery ajax:
function populateTable() {

	console.log("inside populateTable");

	// Empty content string
	var tableContent = '';

	// jQuery AJAX call for JSON
	$.getJSON( '/users', function( data ) {

		// For each item in our JSON, add a table row and cells to the content string
		$.each(data, function(){
			tableContent += '<tr>';
			tableContent += '<td><a href="#" class="linkshowuser" rel="' + this.username + '">' + this.username + '</a></td>';
			tableContent += '<td>' + this.email + '</td>';
			tableContent += '</tr>';
		});

		// Inject the whole content string into our existing HTML table
		$('#userList').html(tableContent);
	});
};


// Fill other contents with a few nmp modules:
var users = [
	{ 'user': 'barney',  'age': 36, 'active': true },
	{ 'user': 'fred',    'age': 40, 'active': false },
	{ 'user': 'pebbles', 'age': 1,  'active': true }
];


var _ = require('lodash');
var repeat = require("repeat-string");

// require elements utilities
var ee = require('elements');
var ready = require('elements/domready');
var zen = require('elements/zen');

function fillPageUsingNpmModules() {
	_.forEach(users, function(u) {
		console.log(u.user + " -- " + u.age);
		// Using elements:
		var elem = zen('div#' + u.user);
		elem.text(u.user).insert(ee('#content'));
	});
}


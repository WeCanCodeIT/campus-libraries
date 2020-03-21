import {
    appendBookList
} from './BookListComponent.js'

import {
    renderCampuses
} from './CampusesComponent.js';

const renderCampus = (campusId) => {
    const campusElement = document.querySelector('main');
    campusElement.innerHTML = '';
    fetch(`http://localhost:8080/api/campuses/${campusId}/`)
        .then(response => response.json())
        .then(campus => createCampusView(campus))
        .then(campusView => campusElement.appendChild(campusView))
        .catch(err => console.error(err));
}

const createCampusView = (campus) => {
    const campusView = document.createElement('section');
    appendCampusTitle(campus, campusView);
    appendBookList(campus.books, campusView);
    appendReturnToCampusesLink(campusView);
    return campusView;
}

const appendCampusTitle = (campus, element) => {
    const campusLocation = document.createElement('h2');
    campusLocation.innerText = campus.location;
    element.appendChild(campusLocation);
}

const appendReturnToCampusesLink = (element) => {
    const returnToCampusesLink = document.createElement('a');
    returnToCampusesLink.innerText = "Return to a listing of all campuses."
    returnToCampusesLink.addEventListener('click', (event) => {
        event.preventDefault();
        renderCampuses();
    });
    element.appendChild(returnToCampusesLink);
}

export {
    renderCampus
}
import {
    renderCampus
} from './CampusComponent.js'

const renderCampuses = () => {
    const campusesElement = document.querySelector('main');
    campusesElement.innerHTML = '';
    fetch('http://localhost:8080/api/campuses/')
        .then(response => response.json())
        .then(campuses => {
            const campusesList = document.createElement('ul');
            campuses.forEach(campus => {
                const campusListing = document.createElement('li');
                campusListing.innerText = campus.location;
                campusListing.addEventListener('click', () => {
                    renderCampus(campus.id);
                });
                campusesList.appendChild(campusListing);
            });
            campusesElement.appendChild(campusesList);
        })
        .catch(err => console.error(err));
    return campusesElement;
}

export {
    renderCampuses
}
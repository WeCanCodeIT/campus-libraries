import {DomMaker} from './DomMaker.js'

fetch("http://localhost:8080/api/campuses/")
    .then(response => response.json())
    .then(campuses => renderCampuses(campuses))
    .catch(err => console.log(err));

const renderCampuses = (campuses) => {
    const anchor = document.querySelector('.anchor');
    campuses.forEach(campus => {
        anchor.appendChild(new DomMaker('section')
            .changeContent(campus.location)
            .addOnClickBehavior(() => {
                navigateToCampus(campus.id);
            })
            .render())
    });
}
const navigateToCampus = (campusId) => {
    fetch(`http://localhost:8080/api/campuses/${campusId}`)
        .then(response => response.json())
        .then(campus => renderCampus(campus))
        .catch(err => console.log(err));
}
const renderCampus = (campus) => {
    const anchor = document.querySelector('.anchor');
    anchor.innerHTML = '';
    const bookList = new DomMaker('ul').changeContent('Books');
    campus.books.forEach(element => {
        bookList.appendChild(new DomMaker('li')
            .changeContent(element.title)
            .render()
        )
    });
    const campusElement =
        new DomMaker('div')
            .changeContent(campus.location)
            .appendChild(bookList.render());
    anchor.appendChild(campusElement.render())
}
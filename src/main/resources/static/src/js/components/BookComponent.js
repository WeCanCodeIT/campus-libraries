import {
    renderCampus
} from './CampusComponent.js'

const renderBookView = (bookId) => {
    const bookElement = document.querySelector('main');
    bookElement.innerHTML = '';
    fetch(`http://localhost:8080/api/books/${bookId}/`)
        .then(response => response.json())
        .then(book => createBookView(book))
        .then(bookView => bookElement.appendChild(bookView))
        .catch(err => console.error(err));
}

const createBookView = (book) => {
    const bookView = document.createElement('section');
    appendBookTitle(book, bookView);
    appendBookAuthor(book, bookView);
    appendReturnToCampusLink(book, bookView);
    return bookView;
}

const appendBookTitle = (book, element) => {
    const bookTitle = document.createElement('h2');
    bookTitle.innerText = book.title;
    element.appendChild(bookTitle);
}

const appendBookAuthor = (book, element) => {
    const bookDescription = document.createElement('p');
    let authorsByline = `Authors: `
    authorsByline += book.authors.map(author => `${author.firstName} ${author.lastName}`)
        .join(', ');
    bookDescription.innerText = authorsByline
    element.appendChild(bookDescription);
}

const appendReturnToCampusLink = (book, element) => {
    const campusLink = document.createElement('a');
    fetch(`http://localhost:8080/api/books/${book.id}/campus/`)
        .then(response => response.json())
        .then(campus => {
            campusLink.innerText = `Return to our ${campus.location} campus.`
            campusLink.addEventListener('click', (event) => {
                event.preventDefault();
                renderCampus(campus.id);
            })
            element.appendChild(campusLink);
        })
        .catch(err => console.error(err));

}

export {
    renderBookView
}
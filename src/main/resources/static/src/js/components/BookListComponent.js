import {
    renderBookView
} from './BookComponent.js'

const appendBookList = (books, element) => {
    const bookList = document.createElement('ul');
    books.forEach(book => {
        const bookTitle = document.createElement('li');
        bookTitle.innerText = book.title;
        bookTitle.addEventListener('click', () => {
            renderBookView(book.id)
        });
        bookList.appendChild(bookTitle);
    });
    element.appendChild(bookList);
}

export {
    appendBookList
}
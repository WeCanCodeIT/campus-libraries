import {
    InputMaker,
    DomMaker
} from "./InputMaker.js"

const createNewBookForm = (authors) => {
    return new DomMaker('form')
        .changeContent('Enter your new book details here:')
        .appendChild(
            new DomMaker('p')
            .changeContent('Title:')
            .render())
        .appendChild(
            new InputMaker()
            .setInputType('text')
            .setPlaceholder('Enter Title Here')
            .addClassName('form__title')
            .render())
        .appendChild(
            new DomMaker('p')
            .changeContent('Author')
            .render())
        .appendChild(
            new InputMaker()
            .setInputType('text')
            .setPlaceholder('First Name')
            .addClassName('form__first-name')
            .render())
        .appendChild(
            new InputMaker()
            .setInputType('text')
            .setPlaceholder('Last Name')
            .addClassName('form__last-name')
            .render())
        .appendChild(
            new DomMaker('button')
            .changeContent('Submit New Book')
            .addOnClickBehavior((event) => {
                event.preventDefault();
                addBook()
            })
            .render()
        )
        .render();
}
const addBook = (event) => {
    const newBook = {
        'title': document.querySelector('.form__title').value,
        'campus': document.querySelector('.form__campus-id').value,
        'authors': [{
            'firstName': document.querySelector('form__first-name'),
            'lastName': document.querySelector('form__last-name')
        }]
    }
    console.log(newBook)
}
export {
    createNewBookForm
}
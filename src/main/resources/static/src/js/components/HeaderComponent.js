const renderHeader = () => {
    const headerElement = document.createElement('header');
    headerElement.innerHTML = `<h1 class='header__title'>We Can Code IT Library Catalog</h1>`
    return headerElement;
}

export {
    renderHeader
}
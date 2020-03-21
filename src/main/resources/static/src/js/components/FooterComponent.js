const renderFooter = () => {
    const footerElement = document.createElement('footer');
    footerElement.innerHTML = `<p class='footer__copyright'>&copy We Can Code IT 2020</p>`
    return footerElement;
}

export {
    renderFooter
}
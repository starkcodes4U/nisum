document.addEventListener('DOMContentLoaded', function() {
   
    const navLinks = document.querySelectorAll('.nav-link');
    

    const pages = document.querySelectorAll('.page');
    
   
    navLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            
            
            const targetPageId = this.getAttribute('href').substring(1);
            
            
            navLinks.forEach(navLink => {
                navLink.classList.remove('active');
            });
            this.classList.add('active');
            
            
            pages.forEach(page => {
                page.classList.remove('active');
                if (page.id === targetPageId) {
                    page.classList.add('active');
                }
            });
            
           
            window.location.hash = targetPageId;
        });
    });
    
    
    const initialHash = window.location.hash.substring(1);
    if (initialHash) {
        const targetLink = document.querySelector(`.nav-link[href="#${initialHash}"]`);
        if (targetLink) {
            targetLink.click();
        }
    }
});
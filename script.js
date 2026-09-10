// ================= SHOW PAGE =================

function showPage(pageName) {

    let pages = document.querySelectorAll(".page");

    pages.forEach(function(page) {
        page.classList.remove("active");
    });

    let selectedPage = document.getElementById(pageName);

    if (selectedPage) {
        selectedPage.classList.add("active");

        window.scrollTo({
            top: 0,
            behavior: "smooth"
        });
    }
}


// ================= DARK MODE =================

function darkMode() {

    document.body.classList.toggle("dark");

}


// ================= PROJECT FILTER =================

function filterProjects(category) {

    let projects = document.querySelectorAll(".project-card");

    projects.forEach(function(project) {

        if (category === "all") {

            project.style.display = "block";

        } else if (project.classList.contains(category)) {

            project.style.display = "block";

        } else {

            project.style.display = "none";

        }

    });

}


// ================= CONTACT FORM =================

let form = document.getElementById("contactForm");

if (form) {

    form.addEventListener("submit", function(event) {

        event.preventDefault();

        let name = document.getElementById("name").value.trim();

        let email = document.getElementById("email").value.trim();

        let message = document.getElementById("message").value.trim();

        let result = document.getElementById("messageResult");


        if (name === "" || email === "" || message === "") {

            result.innerText = "Please fill all fields.";

            result.style.color = "red";

            return;
        }


        if (!email.includes("@") || !email.includes(".")) {

            result.innerText = "Please enter a valid email.";

            result.style.color = "red";

            return;
        }


        result.innerText = "Message submitted successfully!";

        result.style.color = "green";


        form.reset();

    });

}
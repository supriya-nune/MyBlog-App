


const blogPosts = [
    {
        title: "Artists",
        image: "/images/1.png",
        content: "An artist is a person engaged in an activity related to creating art, practicing the arts, or demonstrating an art",
        url: "https://en.wikipedia.org/wiki/Artist",
    },
    {
        title: "Golconda",
        image: "/images/2.jpg",
        content: "Golconda Fort is a famous historical fortress in Hyderabad, known for impressive acoustics and architecture",
        url: "https://en.wikipedia.org/wiki/Golconda",
    },
    {
        title: "Music",
        image: "/images/3.png",
        content: "Music is generally defined as the art of arranging sound to create some combination of form, harmony, melody",
        url: "https://en.wikipedia.org/wiki/Category:Music_blogs",
    },
];

function createBlogArticle(post) {
    const article = document.createElement("article");
    article.classList.add("blog-post");
    article.innerHTML = `
        <div class="blog-image">
            <img src="${post.image}" alt="${post.title}" />
        </div>
        <div class="blog-text">
            <h2>${post.title}</h2>
        </div>
        <div class="blog-container">
            <p>${post.content}</p>
            <a href="${post.url}" class="read-more">Read More</a>
        </div>
    `;
    return article;
}

function addBlogArticles() {
    const blogContent = document.getElementById("blog-container");
    let postCount = 0;
    let currentRow;

    blogPosts.forEach((post, index) => {
        if (postCount % 3 === 0) {
            // Create a new row for every third post
            currentRow = document.createElement("div");
            currentRow.className = "row"; // You may need to adjust the class name
            blogContent.appendChild(currentRow);
        }

        const article = createBlogArticle(post);
        currentRow.appendChild(article);
        postCount++;
    });
}

window.onload = addBlogArticles();

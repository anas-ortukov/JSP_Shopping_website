# Mango Market - Online Shopping Website

Mango Market is an online shopping website developed using JSP (JavaServer Pages) technology. It provides a platform for users to browse and purchase products, manage their orders, and for administrators to manage products, categories, and orders.

## Features

- **Secure Admin Page**: Access to the admin page is restricted, and only users with the role of Admin can access it. Others attempting to access the admin page are filtered out.
- **Secure Clients' Page**: Pages such as order confirmation and my orders require users to log in before accessing them.
- **Redirection Feature**: When a customer wants to confirm their order, the page prompts them to log in. After logging in, the order is confirmed automatically.
- **Detailed "My Orders" Page**: Clients' "My Orders" page provides detailed reports on orders, including information on products, product counts, and prices.
- **CRUD Operations for Admin**: Administrators can perform CRUD operations on categories, products, and view all orders.
- **Order Status Management**: Admins can change the status of orders, and orders can be archived. Archived orders can be viewed and modified in the archives page.
- **User Sign-Up**: New users can sign up for the platform.
- **Password Encryption**: User passwords are encrypted using bcrypt for enhanced security.
  
## Technologies Used

- **Java Backend**: Utilizes Java for server-side logic and business operations.
- **Hibernate JPA**: Object-relational mapping framework for working with PostgreSQL database.
- **JSP (JavaServer Pages)**: Dynamic web pages technology for displaying HTML content with Java code.
- **HTML, CSS, JavaScript**: Front-end technologies for creating interactive and visually appealing user interfaces.
- **Bootstrap**: Front-end framework for responsive and mobile-first web development.
- **Tomcat**: Servlet container that hosts the Java web application.
- **PostgreSQL**: Open-source relational database management system.
- **bcrypt**: Library for password hashing and encryption.

## Installation and Setup

1. Clone the repository to your local machine:

git clone https://github.com/anas-ortukov/JSP_Shopping_website.git

2. Set up the PostgreSQL database called "shopping_db" and configure the connection details in the project's configuration files.

3. Deploy the application to a servlet container such as Apache Tomcat.

4. Access the application through your web browser.

## Usage

1. Log in as an administrator with the provided credentials to access the admin page.

2. Browse the website, add products to the cart, and proceed to checkout.

3. View your orders and manage them from the "My Orders" page.

## Contributing

Contributions are welcome! If you have any suggestions, feature requests, or bug reports, please feel free to submit them.

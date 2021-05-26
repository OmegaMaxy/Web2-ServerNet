<header role="banner">
    <img alt="Datacenter image" src="../../assets/img/The-Datacenter.jpg">
    <nav class="navbar navbar-expand-lg navbar-light nav-enhanced">
        <a class="navbar-brand hideOnMobile ${(currentRoute == "home") ? 'active': ''}" href="Controller?route=home">Home of ServerNet</a>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto">
                <li class="nav-item showOnMobile">
                    <h1>Navigation</h1>
                </li>
                <li class="nav-item showOnMobile ${(currentRoute == "home") ? 'active': ''}">
                    <a class="nav-link" href="Controller?route=home">Home of ServerNet</a>
                </li>
                <li class="nav-item ${(currentRoute == "overview") ? 'active': ''}">
                    <a class="nav-link" href="Controller?route=overview">Manage servers</a>
                </li>
                <li class="nav-item ${(currentRoute == "create") ? 'active': ''}">
                    <a class="nav-link" href="Controller?route=create">Add server</a>
                </li>
                <li class="nav-item ${(currentRoute == "search") ? 'active': ''}">
                    <a class="nav-link" href="Controller?route=search">Search</a>
                </li>
                <li class="nav-item ${(currentRoute == "shop-basket") ? 'active': ''}">
                    <a class="nav-link" href="Controller?route=shop-basket">Shopping Cart</a>
                </li>
                <li class="nav-item nav-special ${(currentRoute == "shop") ? 'active': ''}">
                    <a class="nav-link" href="Controller?route=shop">Shop</a>
                </li>
            </ul>
            <ul class="navbar-nav darkmode-buttons" title="Change to darkmode!">
                <li class="nav-item">
                    <form action="Controller?route=darkmode" method="POST">
                        <input type="hidden" name="originRoute" value="${currentRoute}">
                        <input class="nav-link" type="submit" name="option" value="light">
                    </form>
                </li>
                <li class="nav-item">
                    <form action="Controller?route=darkmode" method="POST">
                        <input type="hidden" name="originRoute" value="${currentRoute}">
                        <input class="nav-link" type="submit" name="option" value="dark">
                    </form>
                </li>
            </ul>
        </div>
    </nav>
</header>
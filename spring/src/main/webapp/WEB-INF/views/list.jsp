<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách sản phẩm</title>
    <style>
        /* ===== Body & Typography ===== */
        body {
            font-family: "Segoe UI", Arial, sans-serif;
            background: #f5f6fa; /* nền pastel dịu mắt */
            margin: 0;
            padding: 20px 0;
            color: #2d3436;
        }

        h2 {
            text-align: center;
            margin-bottom: 30px;
            font-weight: 700;
            letter-spacing: 1px;
            background: linear-gradient(135deg, #6a11cb, #2575fc); /* Gradient text để nổi bật */
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            background-clip: text;
            text-fill-color: transparent;
            font-size: 2.2em; /* Tăng size nhẹ để nổi bật hơn */
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 8px; /* Khoảng cách giữa icon và text */
        }

        /* ===== Container ===== */
        .container {
            width: 90%;
            max-width: 1000px;
            margin: 0 auto;
        }

        /* ===== Top Bar: Add & Search ===== */
        .top-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        .add-btn {
            background: linear-gradient(135deg, #6a11cb, #2575fc);
            color: #fff;
            padding: 10px 20px;
            border-radius: 12px;
            font-weight: 600;
            text-decoration: none;
            box-shadow: 0 4px 12px rgba(0,0,0,0.15);
            transition: all 0.3s ease;
        }
        .add-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 6px 20px rgba(0,0,0,0.2);
        }

        .search-form {
            display: flex;
        }
        .search-input {
            padding: 10px 14px;
            border-radius: 12px 0 0 12px;
            border: 1px solid #ccc;
            outline: none;
            width: 220px;
            transition: 0.3s;
        }
        .search-input:focus {
            border-color: #6a11cb;
            box-shadow: 0 0 6px rgba(106,17,203,0.3);
        }
        .search-btn {
            background: linear-gradient(135deg, #fc5c7d, #6a82fb);
            color: #fff;
            border: none;
            padding: 10px 16px;
            border-radius: 0 12px 12px 0;
            cursor: pointer;
            font-weight: 600;
            box-shadow: 0 4px 12px rgba(0,0,0,0.15);
            transition: all 0.3s ease;
        }
        .search-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 6px 20px rgba(0,0,0,0.2);
        }

        /* ===== Table ===== */
        table {
            width: 100%;
            border-collapse: collapse;
            border-radius: 16px;
            overflow: hidden;
            box-shadow: 0 8px 24px rgba(0,0,0,0.08);
            background: #fff;
        }

        th, td {
            padding: 14px 12px;
            text-align: center;
            border-bottom: 1px solid #eee;
        }
        th {
            background: #6c5ce7; /* pastel xanh tím */
            color: #fff;
            text-transform: uppercase;
            font-weight: 600;
        }
        tr:hover {
            background-color: #f0f4f8;
            transition: 0.3s;
        }

        /* ===== Action Links (Edit/Delete) ===== */
        .action-links a {
            margin: 0 4px;
            padding: 6px 14px;
            border-radius: 10px;
            color: #fff;
            font-size: 13px;
            text-decoration: none;
            font-weight: 600;
            transition: all 0.3s ease;
        }
        .edit-link {
            background-color: #55efc4; /* pastel xanh lá */
        }
        .edit-link:hover {
            background-color: #00b894;
        }
        .delete-link {
            background-color: #ff7675; /* pastel đỏ */
        }
        .delete-link:hover {
            background-color: #e17055;
        }

        /* ===== Flash Message ===== */
        #flashMessage {
            background-color: #dfe6e9;
            color: #2d3436;
            padding: 14px 20px;
            border-radius: 12px;
            margin-bottom: 20px;
            font-weight: 600;
            text-align: center;
            box-shadow: 0 6px 16px rgba(0,0,0,0.08);
            animation: fadeIn 0.5s ease;
        }
        @keyframes fadeIn {
            from {opacity: 0; transform: translateY(-10px);}
            to {opacity: 1; transform: translateY(0);}
        }

        /* ===== Delete Modal ===== */
        #deleteModal {
            display: none;
            position: fixed;
            top:0; left:0; right:0; bottom:0;
            background: rgba(0,0,0,0.4);
            justify-content: center;
            align-items: center;
            z-index: 1000;
        }
        #deleteModal .modal-content {
            background: #fff;
            padding: 25px 35px;
            border-radius: 20px;
            width: 380px;
            text-align: center;
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
            animation: modalFade 0.3s ease;
        }
        #deleteModal button {
            padding: 10px 20px;
            border-radius: 12px;
            border: none;
            cursor: pointer;
            margin: 0 8px;
            font-weight: 600;
            transition: all 0.3s ease;
        }
        #deleteModal .btn-cancel {
            background-color: #b2bec3;
            color: #fff;
        }
        #deleteModal .btn-cancel:hover {
            background-color: #636e72;
        }
        #deleteModal .btn-delete {
            background-color: #ff6b6b;
            color: #fff;
        }
        #deleteModal .btn-delete:hover {
            background-color: #e74c3c;
        }
        @keyframes modalFade {
            from {opacity: 0; transform: scale(0.9);}
            to {opacity: 1; transform: scale(1);}
        }

        /* ===== Responsive ===== */
        @media (max-width: 600px) {
            .top-bar {
                flex-direction: column;
                align-items: stretch;
            }
            .search-form {
                margin-top: 10px;
            }
            .search-input {
                width: 100%;
            }
        }


    </style>
</head>
<body>
<c:if test="${not empty message}">
    <div id="flashMessage"
         style="background-color: #d4edda; color: #155724; padding: 10px;
                border-radius: 5px; margin-bottom: 15px; transition: opacity 0.5s ease;">
        ✅ ${message}
    </div>

    <script>
        // Sau 5 giây thì làm mờ và ẩn đi
        setTimeout(() => {
            const msg = document.getElementById("flashMessage");
            if (msg) {
                msg.style.opacity = "0"; // làm mờ dần
                setTimeout(() => msg.remove(), 500); // xóa hẳn sau 0.5s
            }
        }, 5000);
    </script>
</c:if>

<h2>Danh sách sản phẩm</h2>

<div class="top-bar">
    <a href="${pageContext.request.contextPath}/products/add" class="add-btn">➕ Thêm sản phẩm</a>
    <form class="search-form" action="${pageContext.request.contextPath}/products/search" method="get">
        <input type="text" name="keyword" class="search-input" placeholder="Tìm theo tên sản phẩm..." value="${keyword != null ? keyword : ''}">
        <button type="submit" class="search-btn">🔍 Tìm kiếm</button>
    </form>
</div>

<table>
    <tr>
        <th>STT</th>
        <th>Tên</th>
        <th>Giá</th>
        <th>Hành động</th>
    </tr>

    <c:forEach var="p" items="${products}" varStatus="status">
        <tr>
            <td>${status.index+1}</td>
            <td>${p.name}</td>
            <td><fmt:formatNumber value="${p.price}" type="number" groupingUsed="true"/> ₫</td>
            <td class="action-links">
                <a href="javascript:void(0);"
                   class="delete-link"
                   onclick="openDeleteModal('${pageContext.request.contextPath}/products/delete/${p.id}')">
                    Xóa
                </a>

            </td>
        </tr>
    </c:forEach>
</table>
<!-- Hộp thoại xác nhận xóa -->
<div id="deleteModal" style="
    display: none;
    position: fixed;
    top: 0; left: 0; right: 0; bottom: 0;
    background: rgba(0,0,0,0.5);
    align-items: center;
    justify-content: center;
    z-index: 1000;
">
    <div style="
        background: white;
        padding: 20px 30px;
        border-radius: 10px;
        width: 350px;
        text-align: center;
        box-shadow: 0 2px 10px rgba(0,0,0,0.3);
        animation: fadeIn 0.3s ease;
    ">
        <h3 style="margin-bottom: 10px;">Xác nhận xóa</h3>
        <p>Bạn có chắc chắn muốn xóa sản phẩm này không?</p>
        <div style="margin-top: 20px;">
            <button id="confirmDelete" style="
                background-color: #dc3545;
                color: white;
                border: none;
                padding: 8px 16px;
                border-radius: 5px;
                cursor: pointer;
            ">Xóa</button>
            <button id="cancelDelete" style="
                background-color: #6c757d;
                color: white;
                border: none;
                padding: 8px 16px;
                border-radius: 5px;
                cursor: pointer;
                margin-left: 10px;
            ">Hủy</button>
        </div>
    </div>
</div>

<script>
    let deleteUrl = "";

    // Khi nhấn nút xóa → hiện modal
    function openDeleteModal(url) {
        deleteUrl = url;
        document.getElementById("deleteModal").style.display = "flex";
    }

    // Bấm Hủy → đóng modal
    document.getElementById("cancelDelete").addEventListener("click", () => {
        document.getElementById("deleteModal").style.display = "none";
    });

    // Bấm Xóa → chuyển trang đến URL xóa
    document.getElementById("confirmDelete").addEventListener("click", () => {
        window.location.href = deleteUrl;
    });

</script>

<style>
    @keyframes fadeIn {
        from {opacity: 0; transform: scale(0.9);}
        to {opacity: 1; transform: scale(1);}
    }
</style>
<!-- Canvas hiệu ứng -->
<canvas id="effectCanvas" style="
    position: fixed;
    top: 0; left: 0;
    width: 100%; height: 100%;
    pointer-events: none;
    z-index: 2000;
"></canvas>
<script>
    // Hàm chung để lấy canvas và kích thước
    function initCanvas() {
        var canvas = document.getElementById("effectCanvas");
        var ctx = canvas.getContext("2d");
        canvas.width = window.innerWidth;
        canvas.height = window.innerHeight;
        return { canvas, ctx };
    }

    // Hiệu ứng tuyết rơi nâng cao (beautiful snow with mouse swirl)
    function startSnow() {
        var { canvas, ctx } = initCanvas();
        var flakeCount = 300; // Tăng để dày đặc hơn, đẹp hơn
        var flakes = [];
        var mX = -100, mY = -100;

        function reset(flake) {
            flake.x = Math.floor(Math.random() * canvas.width);
            flake.y = 0;
            flake.size = (Math.random() * 3) + 2;
            flake.speed = (Math.random() * 1) + 0.5;
            flake.velY = flake.speed;
            flake.velX = 0;
            flake.opacity = (Math.random() * 0.5) + 0.3; // Opacity random để mờ ảo đẹp
            flake.stepSize = (Math.random()) / 30 * 1; // Tăng wavy effect
        }

        for (var i = 0; i < flakeCount; i++) {
            var x = Math.floor(Math.random() * canvas.width),
                y = Math.floor(Math.random() * canvas.height),
                size = (Math.random() * 3) + 2,
                speed = (Math.random() * 1) + 0.5,
                opacity = (Math.random() * 0.5) + 0.3;

            flakes.push({
                speed: speed,
                velY: speed,
                velX: 0,
                x: x,
                y: y,
                size: size,
                stepSize: (Math.random()) / 30,
                step: 0,
                opacity: opacity
            });
        }

        function snow() {
            ctx.clearRect(0, 0, canvas.width, canvas.height);

            for (var i = 0; i < flakeCount; i++) {
                var flake = flakes[i],
                    x = mX,
                    y = mY,
                    minDist = 150,
                    x2 = flake.x,
                    y2 = flake.y;

                var dist = Math.sqrt((x2 - x) * (x2 - x) + (y2 - y) * (y2 - y)),
                    dx = x2 - x,
                    dy = y2 - y;

                if (dist < minDist) {
                    var force = minDist / (dist * dist),
                        xcomp = (x - x2) / dist,
                        ycomp = (y - y2) / dist,
                        deltaV = force / 2;

                    flake.velX -= deltaV * xcomp;
                    flake.velY -= deltaV * ycomp;
                } else {
                    flake.velX *= .98;
                    if (flake.velY <= flake.speed) {
                        flake.velY = flake.speed
                    }
                    flake.velX += Math.cos(flake.step += .05) * flake.stepSize;
                }

                ctx.fillStyle = "rgba(255,255,255," + flake.opacity + ")";
                flake.y += flake.velY;
                flake.x += flake.velX;

                if (flake.y >= canvas.height || flake.y <= 0) {
                    reset(flake);
                }

                if (flake.x >= canvas.width || flake.x <= 0) {
                    reset(flake);
                }

                ctx.beginPath();
                ctx.arc(flake.x, flake.y, flake.size, 0, Math.PI * 2);
                ctx.fill();
            }
        }

        var animationId;
        function animateSnow() {
            snow();
            animationId = requestAnimationFrame(animateSnow);
        }
        animateSnow();

        // Mouse interaction
        canvas.addEventListener("mousemove", function(e) {
            mX = e.clientX,
                mY = e.clientY
        });

        // Dừng sau 10 giây
        setTimeout(() => {
            cancelAnimationFrame(animationId);
            ctx.clearRect(0, 0, canvas.width, canvas.height);
        }, 10000);
    }

    // Hiệu ứng pháo hoa nâng cao (beautiful fireworks with trails and gravity)
    function startFireworks() {
        var { canvas, ctx } = initCanvas();
        var cw = canvas.width,
            ch = canvas.height,
            fireworks = [],
            particles = [],
            hue = 120,
            limiterTotal = 5,
            limiterTick = 0,
            timerTotal = 30, // Tăng tần suất bắn để đẹp hơn (mỗi 30 tick)
            timerTick = 0;

        function random(min, max) {
            return Math.random() * (max - min) + min;
        }

        function calculateDistance(p1x, p1y, p2x, p2y) {
            var xDistance = p1x - p2x,
                yDistance = p1y - p2y;
            return Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
        }

        function Firework(sx, sy, tx, ty) {
            this.x = sx;
            this.y = sy;
            this.sx = sx;
            this.sy = sy;
            this.tx = tx;
            this.ty = ty;
            this.distanceToTarget = calculateDistance(sx, sy, tx, ty);
            this.distanceTraveled = 0;
            this.coordinates = [];
            this.coordinateCount = 3;
            while (this.coordinateCount--) {
                this.coordinates.push([this.x, this.y]);
            }
            this.angle = Math.atan2(ty - sy, tx - sx);
            this.speed = 2;
            this.acceleration = 1.05;
            this.brightness = random(50, 70);
            this.targetRadius = 1;
        }

        Firework.prototype.update = function(index) {
            this.coordinates.pop();
            this.coordinates.unshift([this.x, this.y]);

            if (this.targetRadius < 8) {
                this.targetRadius += 0.3;
            } else {
                this.targetRadius = 1;
            }

            this.speed *= this.acceleration;

            var vx = Math.cos(this.angle) * this.speed,
                vy = Math.sin(this.angle) * this.speed;
            this.distanceTraveled = calculateDistance(this.sx, this.sy, this.x + vx, this.y + vy);

            if (this.distanceTraveled >= this.distanceToTarget) {
                createParticles(this.tx, this.ty);
                fireworks.splice(index, 1);
            } else {
                this.x += vx;
                this.y += vy;
            }
        };

        Firework.prototype.draw = function() {
            ctx.beginPath();
            ctx.moveTo(this.coordinates[this.coordinates.length - 1][0], this.coordinates[this.coordinates.length - 1][1]);
            ctx.lineTo(this.x, this.y);
            ctx.strokeStyle = 'hsl(' + hue + ', 100%, ' + this.brightness + '%)';
            ctx.stroke();

            ctx.beginPath();
            ctx.arc(this.tx, this.ty, this.targetRadius, 0, Math.PI * 2);
            ctx.stroke();
        };

        function Particle(x, y) {
            this.x = x;
            this.y = y;
            this.coordinates = [];
            this.coordinateCount = 5;
            while (this.coordinateCount--) {
                this.coordinates.push([this.x, this.y]);
            }
            this.angle = random(0, Math.PI * 2);
            this.speed = random(1, 10);
            this.friction = 0.95;
            this.gravity = 1;
            this.hue = random(hue - 50, hue + 50);
            this.brightness = random(50, 80);
            this.alpha = 1;
            this.decay = random(0.015, 0.03);
        }

        Particle.prototype.update = function(index) {
            this.coordinates.pop();
            this.coordinates.unshift([this.x, this.y]);
            this.speed *= this.friction;
            this.x += Math.cos(this.angle) * this.speed;
            this.y += Math.sin(this.angle) * this.speed + this.gravity;
            this.alpha -= this.decay;

            if (this.alpha <= this.decay) {
                particles.splice(index, 1);
            }
        };

        Particle.prototype.draw = function() {
            ctx.beginPath();
            ctx.moveTo(this.coordinates[this.coordinates.length - 1][0], this.coordinates[this.coordinates.length - 1][1]);
            ctx.lineTo(this.x, this.y);
            ctx.strokeStyle = 'hsla(' + this.hue + ', 100%, ' + this.brightness + '%, ' + this.alpha + ')';
            ctx.stroke();
        };

        function createParticles(x, y) {
            var particleCount = 50; // Tăng để nổ đẹp hơn
            while (particleCount--) {
                particles.push(new Particle(x, y));
            }
        }

        var animationId;
        function loop() {
            ctx.globalCompositeOperation = 'destination-out';
            ctx.fillStyle = 'rgba(0, 0, 0, 0.2)'; // Giảm opacity để trail rõ ràng nhưng không tối màn hình
            ctx.fillRect(0, 0, cw, ch);
            ctx.globalCompositeOperation = 'lighter';

            var i = fireworks.length;
            while (i--) {
                fireworks[i].draw();
                fireworks[i].update(i);
            }

            i = particles.length;
            while (i--) {
                particles[i].draw();
                particles[i].update(i);
            }

            if (timerTick >= timerTotal) {
                fireworks.push(new Firework(cw / 2, ch, random(0, cw), random(0, ch / 2)));
                timerTick = 0;
            } else {
                timerTick++;
            }

            animationId = requestAnimationFrame(loop);
        }
        loop();

        // Dừng sau 10 giây
        setTimeout(() => {
            cancelAnimationFrame(animationId);
            ctx.clearRect(0, 0, cw, ch);
        }, 10000);
    }

    // Kích hoạt hiệu ứng dựa trên message
    var flashMessage = document.getElementById("flashMessage");
    if (flashMessage) {
        var messageText = flashMessage.textContent.trim().replace('✅ ', '').toLowerCase();
        if (messageText.includes('thêm')) {
            startSnow();
        } else if (messageText.includes('xóa')) {
            startFireworks();
        }
    }

    // Handle resize
    window.addEventListener('resize', initCanvas);
</script>
</body>
</html>
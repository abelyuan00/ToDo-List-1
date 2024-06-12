document.addEventListener("DOMContentLoaded", () => {
    const taskList = document.getElementById("task-list");
    const form = document.getElementById("add-task-form");
    const taskInput = document.getElementById("task-input");

    function loadTasks() {
        fetch("/api/todo")
            .then(response => response.json())
            .then(tasks => {
                taskList.innerHTML = "";
                tasks.forEach((task) => {
                    const li = document.createElement("li");
                    li.className = task.done ? "done" : "";
                    li.innerHTML = `
                        ${task.description}
                        <div>
                            <button onclick="completeTask(${task.id})">Done</button>
                            <button onclick="deleteTask(${task.id})">Delete</button>
                        </div>
                    `;
                    taskList.appendChild(li);
                });
            });
    }

    form.addEventListener("submit", (e) => {
        e.preventDefault();
        const description = taskInput.value;
        fetch("/api/todo/addTask?description=" + encodeURIComponent(description), { method: "POST" })
            .then(() => {
                taskInput.value = "";
                loadTasks();
            });
    });

    window.completeTask = (id) => {
        fetch("/api/todo/done?id=" + id, { method: "POST" })
            .then(loadTasks);
    };

    window.deleteTask = (id) => {
        fetch("/api/todo/delete?id=" + id, { method: "POST" })
            .then(loadTasks);
    };

    loadTasks();
});

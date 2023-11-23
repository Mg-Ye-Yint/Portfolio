document.addEventListener('DOMContentLoaded', function () {
    const container = document.querySelector('.grid-container');
  
    for (let i = 0; i < 20; i++) {
      const randomHeight = Math.floor(Math.random() * 200) + 50;
  
      const div = document.createElement('div');
      div.className = 'grid-item';
      div.style.height = `${randomHeight}px`;
  
      container.appendChild(div);
    }
  });
  
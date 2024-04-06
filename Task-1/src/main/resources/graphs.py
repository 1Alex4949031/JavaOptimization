import matplotlib.pyplot as plt
import pandas as pd

# Чтение данных из CSV
data = pd.read_csv("memory_usage.csv")

# Построение графиков
plt.figure(figsize=(15, 12))

# Использованная память
plt.plot(data['Used Memory'], label='Used Memory', color='blue')

# Свободная память
plt.plot(data['Free Memory'], label='Free Memory', color='green')

# Общая память
plt.plot(data['Total Memory'], label='Total Memory', color='red')

# Максимальная память
plt.plot(data['Max Memory'], label='Max Memory', color='purple')

plt.title('Memory Usage Over Time')
plt.xlabel('Measurement Intervals')
plt.ylabel('Memory in Bytes')
plt.legend()
plt.grid(True)

plt.show()
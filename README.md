<<<<<<< HEAD
Ivan Siarheyenka / Иван Сергеенко

Класс Animal 

Описание:

Класс Animal представляет животное в симулируемой экосистеме.

Поля:

name: Название животного (строка).
population: Численность популяции животного (целое число).
type: Тип животного (хищник или травоядное), значение из перечисления AnimalType.
foodRequirement: Количество еды, необходимое для выживания одного животного (число с плавающей точкой).
reproductionRate: Коэффициент размножения (число с плавающей точкой).
Перечисление AnimalType:
HERBIVORE: Травоядное.
CARNIVORE: Хищник.

Конструктор:
Animal(String name, int population, AnimalType type, double foodRequirement, double reproductionRate): Создает новый объект Animal с заданными параметрами.

Методы:
getName(): Возвращает имя животного.
getPopulation(): Возвращает численность популяции.
setPopulation(int population): Устанавливает численность популяции.
getType(): Возвращает тип животного.
setType(AnimalType type): Устанавливает тип животного.
setFoodRequirement(double foodRequirement): Устанавливает потребность в еде.
setReproductionRate(double reproductionRate): Устанавливает коэффициент размножения.
seekFood(EcoSystem ecosystem): Осуществляет поиск пищи в экосистеме. Травоядные едят растения, хищники охотятся на травоядных.
eatPlants(EcoSystem ecosystem): (Приватный метод) Травоядные животные едят растения в экосистеме.
huntAnimals(EcoSystem ecosystem): (Приватный метод) Хищники охотятся на травоядных животных в экосистеме.
adjustPopulation(int foodEaten, int totalFoodNeeded): (Приватный метод) Регулирует численность популяции в зависимости от количества съеденной пищи.
applyNaturalMortality(double deathRate): Применяет естественную смертность к популяции.
toString(): Возвращает строковое представление объекта Animal.
Класс Climate
Описание:
Класс Climate представляет климатические условия в экосистеме.
Поля:
temperature: Температура (число с плавающей точкой).
humidity: Влажность (число с плавающей точкой).
Конструктор:
Climate(double temperature, double humidity): Создает новый объект Climate с заданными температурой и влажностью.
Методы:
toString(): Возвращает строковое представление объекта Climate.
getHumidity(): Возвращает значение влажности.
getTemperature(): Возвращает значение температуры.
Класс EcoSystem
Описание:
Класс EcoSystem представляет экосистему, содержащую животных, растения, ресурсы и климатические условия.
Поля:
animals: Список животных в экосистеме.
plants: Список растений в экосистеме.
resources: Список ресурсов в экосистеме.
climate: Климатические условия в экосистеме.
environment: Среда экосистемы.
Конструктор:
EcoSystem(): Создает новый объект EcoSystem.
Методы:
clearSimulation(): Очищает данные симуляции, удаляя все животные, растения, ресурсы и климатические условия.
createNewSimulation(): Создает новую симуляцию, заполняя экосистему случайными животными, растениями и ресурсами.
setCustomConditions(double temperature, double humidity): Устанавливает заданные климатические условия.
simulateOneDay(): Симулирует один день в экосистеме.
applyNaturalMortality(): (Приватный метод) Применяет естественную смертность к животным и растениям.
addAnimal(Animal animal): Добавляет животное в экосистему.
removeAnimal(String animalName): Удаляет животное из экосистемы по имени.
addPlant(Plant plant): Добавляет растение в экосистему.
removePlant(String plantName): Удаляет растение из экосистемы по имени.
addResource(Resource resource): Добавляет ресурс в экосистему.
removeResource(Resource.ResourceType resourceType): Удаляет ресурс из экосистемы по типу.
showEcosystem(): Выводит информацию о текущем состоянии экосистемы.
toString(): Возвращает строковое представление объекта EcoSystem.
getClimate(): Возвращает климатические условия.
getAnimals(): Возвращает список животных.
getPlants(): Возвращает список растений.
getResources(): Возвращает список ресурсов.
Класс Environment
Описание:
Класс Environment представляет среду экосистемы,
включая такие параметры, как температура, влажность,
уровень воды, минералов и солнечного света.
Поля:
temperature: Температура (число с плавающей точкой).
humidity: Влажность (число с плавающей точкой).
water: Уровень воды (число с плавающей точкой).
minerals: Уровень минералов (число с плавающей точкой).
sunlight: Уровень солнечного света (число с плавающей точкой).
random: Генератор случайных чисел (объект Random).
dayOfYear: День года (целое число).
Константы:
OPTIMAL_WATER: Оптимальный уровень воды.
OPTIMAL_MINERALS: Оптимальный уровень минералов.
OPTIMAL_SUNLIGHT: Оптимальный уровень солнечного света.
OPTIMAL_HUMIDITY: Оптимальная влажность.
TEMPERATURE_STRESS_LOW: Нижний порог температуры, вызывающий стресс.
TEMPERATURE_STRESS_HIGH: Верхний порог температуры, вызывающий стресс.
Конструктор:
Environment(): Создает новый объект Environment с
установленными значениями по умолчанию.
Методы:
update(List<Animal> animals, List<Plant> plants):
Обновляет состояние среды, включая потребление ресурсов
растениями, изменение климата, пополнение ресурсов,
возможное выпадение осадков и расчет уровня солнечного света.
applyRainEvent(): (Приватный метод) Симулирует выпадение осадков,
увеличивая уровень воды и минералов.
consumeResources(List<Plant> plants): (Приватный метод)
Растения потребляют воду и минералы из среды.
calculateSunlight(): (Приватный метод) Рассчитывает уровень
солнечного света в зависимости от дня года.
growPlants(List<Plant> plants): Симулирует рост растений,
учитывая факторы окружающей среды.
calculateGrowthFactor(Environment environment, Plant plant):
(Приватный метод) Вычисляет фактор роста растения на основе
уровня воды, минералов, солнечного света, температуры и влажности.
calculateTemperatureFactor(double currentTemperature):
(Приватный метод) Вычисляет фактор влияния температуры
на рост растений.
replenishResources(): Пополняет уровень воды и минералов
в среде.
applyClimateChange(): Симулирует изменение климата,
изменяя температуру и влажность в зависимости от времени года.
setResourcesFromList(List<Resource> resources):
Устанавливает уровень ресурсов на основе списка объектов Resource.
calculateFactor(double currentValue, double optimalValue):
(Приватный method) Вычисляет фактор влияния текущего значения
ресурса на рост растений по отношению к оптимальному значению.
toString(): Возвращает строковое представление объекта Environment.
getHumidity(): Возвращает значение влажности.
setMinerals(double minerals): Устанавливает уровень минералов.
setSunlight(double sunlight): Устанавливает уровень солнечного света.
setHumidity(double humidity): Устанавливает значение влажности.
getTemperature(): Возвращает значение температуры.
setTemperature(double temperature): Устанавливает значение температуры.
getSunlight(): Возвращает уровень солнечного света.
setWater(double water): Устанавливает уровень воды.
getMinerals(): Возвращает уровень минералов.
getWater(): Возвращает уровень воды.
getDayOfYear(): Возвращает день года.
Класс Plant
Описание:
Класс Plant представляет растение в симулируемой экосистеме.
Поля:
name: Название растения (строка).
quantity: Количество растений (целое число).
growthRate: Коэффициент роста (число с плавающей точкой).
waterNeed: Потребность в воде (число с плавающей точкой).
mineralsNeed: Потребность в минералах (число с плавающей точкой).
Конструктор:
Plant(String name, int quantity, double growthRate, double waterNeed, double mineralsNeed): Создает новый объект Plant с заданными параметрами.
Методы:
getName(): Возвращает имя растения.
getQuantity(): Возвращает количество растений.
setQuantity(int quantity): Устанавливает количество растений.
setGrowthRate(double growthRate): Устанавливает коэффициент роста.
getWaterNeed(): Возвращает потребность в воде.
setWaterNeed(double waterNeed): Устанавливает потребность в воде.
getMineralsNeed(): Возвращает потребность в минералах.
setMineralsNeed(double mineralsNeed): Устанавливает потребность в минералах.
grow(double waterFactor, double mineralFactor, double sunlightFactor): Симулирует рост растения с учетом факторов воды, минералов и солнечного света.
applyNaturalMortality(double deathRate): Применяет естественную смертность к количеству растений.
toString(): Возвращает строковое представление объекта Plant.
Класс Resource
Описание:
Класс Resource представляет ресурс в экосистеме,
такой как вода, минералы или солнечный свет.
Поля:
type: Тип ресурса (значение из перечисления ResourceType).
amount: Количество ресурса (число с плавающей точкой).
Перечисление ResourceType:
WATER: Вода.
MINERALS: Минералы.
SUNLIGHT: Солнечный свет.
Конструктор:
Resource(ResourceType type, double amount):
Создает новый объект Resource с заданным типом и количеством.
Методы:
getType(): Возвращает тип ресурса.
getAmount(): Возвращает количество ресурса.
setAmount(double amount): Устанавливает количество ресурса.
toString(): Возвращает строковое представление объекта Resource.
Класс Simulation
Описание:
Класс Simulation управляет симуляцией экосистемы,
включая создание, загрузку, сохранение и выполнение симуляции.
Поля:
ecosystem: Объект EcoSystem, представляющий симулируемую экосистему.
repository: Объект EcosystemRepository,
отвечающий за сохранение и загрузку симуляций.
predictionEngine: Объект PredictionEngine,
используемый для прогнозирования изменений в экосистеме.
simulationDay: Текущий день симуляции (целое число).
Конструктор:
Simulation(String dataDirectory): Создает новый объект Simulation
с заданной директорией для хранения данных симуляции.
Методы:
getPredictionEngine(): Возвращает объект PredictionEngine.
createNewSimulation(): Создает новую симуляцию экосистемы.
loadSimulation(String filename): Загружает симуляцию из файла.
saveSimulation(): Сохраняет текущую симуляцию в файл.
simulateOneDay(): Выполняет симуляцию одного дня в экосистеме.
showEcosystem(): Выводит информацию о текущем состоянии экосистемы.
getEcosystem(): Возвращает объект EcoSystem.
getRepository(): Возвращает объект EcosystemRepository.
Класс FileHandler
Описание:
Класс FileHandler отвечает за загрузку
данных о животных, растениях и ресурсах из файлов.
Методы:
loadAnimals(String filename): Загружает данные о животных
из файла с заданным именем.
loadPlants(String filename): Загружает данные о растениях
из файла с заданным именем.
loadResources(String filename): Загружает данные о ресурсах
из файла с заданным именем.
Класс Logger
Описание:
Класс Logger отвечает за ведение журнала
симуляции, записывая информацию о событиях
и состоянии экосистемы в файл.
Поля:
logFile: Имя файла журнала.
currentLogLevel: Текущий уровень логирования
(значение из перечисления LogLevel).
Перечисление LogLevel:
DEBUG: Отладочные сообщения.
INFO: Информационные сообщения.
WARN: Предупреждения.
ERROR: Ошибки.
Конструктор:
Logger(String logFile): Создает новый объект Logger
с заданным именем файла журнала.
Методы:
setLogLevel(LogLevel logLevel): Устанавливает уровень логирования.
debug(String message): Записывает отладочное сообщение в журнал.
info(String message): Записывает информационное сообщение в журнал.
warn(String message): Записывает предупреждение в журнал.
error(String message): Записывает сообщение об ошибке в журнал.
logDay(int day): Записывает информацию о текущем дне симуляции.
logEcosystem(EcoSystem ecosystem): Записывает информацию о
состоянии экосистемы.
Класс PredictionEngine
Описание:
Класс PredictionEngine используется для
прогнозирования изменений в экосистеме.
Методы:
predictPopulationChanges(EcoSystem ecosystem):
Генерирует прогноз изменений популяции животных и растений
в экосистеме.
predictAnimalPopulation(Animal animal, EcoSystem ecosystem):
(Приватный метод) Прогнозирует изменение
популяции конкретного вида животных.
predictPlantGrowth(Plant plant, EcoSystem ecosystem):
(Приватный метод) Прогнозирует рост
конкретного вида растений.
predictClimateTrends(EcoSystem ecosystem):
(Приватный метод) Прогнозирует изменения
климата в экосистеме.
checkFoodAvailability(Animal animal, EcoSystem ecosystem):
(Приватный метод) Проверяет наличие пищи
для конкретного вида животных.
calculatePlantGrowthFactor(Plant plant, Resource resource, double optimalValue):
(Приватный метод) Вычисляет фактор роста
растений на основе доступности
конкретного ресурса.
=======
`Ivan Siarheyenka / Иван Сергеенко`

### Класс `Animal`

**Описание:**

Класс `Animal` представляет животное в симулируемой экосистеме. 

**Поля:**

* `name`:  Название животного (строка).
* `population`:  Численность популяции животного (целое число).
* `type`:  Тип животного (хищник или травоядное),  значение из перечисления `AnimalType`.
* `foodRequirement`:  Количество еды,  необходимое для выживания одного животного (число с плавающей точкой).
* `reproductionRate`:  Коэффициент размножения (число с плавающей точкой).

**Перечисление `AnimalType`:**

* `HERBIVORE`: Травоядное.
* `CARNIVORE`: Хищник.

**Конструктор:**

* `Animal(String name, int population, AnimalType type, double foodRequirement, double reproductionRate)`:  Создает новый объект `Animal` с заданными параметрами.

**Методы:**

* `getName()`: Возвращает имя животного.
* `getPopulation()`:  Возвращает численность популяции.
* `setPopulation(int population)`:  Устанавливает численность популяции.
* `getType()`:  Возвращает тип животного.
* `setType(AnimalType type)`:  Устанавливает тип животного.
* `setFoodRequirement(double foodRequirement)`:  Устанавливает потребность в еде.
* `setReproductionRate(double reproductionRate)`:  Устанавливает коэффициент размножения.
* `seekFood(EcoSystem ecosystem)`:  Осуществляет поиск пищи в экосистеме. Травоядные едят растения,  хищники охотятся на травоядных.
* `eatPlants(EcoSystem ecosystem)`:  (Приватный метод)  Травоядные животные едят растения в экосистеме.
* `huntAnimals(EcoSystem ecosystem)`:  (Приватный метод)  Хищники охотятся на травоядных животных в экосистеме.
* `adjustPopulation(int foodEaten, int totalFoodNeeded)`:  (Приватный метод)  Регулирует численность популяции в зависимости от количества съеденной пищи.
* `applyNaturalMortality(double deathRate)`:  Применяет естественную смертность к популяции.
* `toString()`:  Возвращает строковое представление объекта `Animal`.

---

### Класс `Climate`

**Описание:**

Класс `Climate`  представляет климатические условия в экосистеме.

**Поля:**

* `temperature`:  Температура (число с плавающей точкой).
* `humidity`:  Влажность (число с плавающей точкой).

**Конструктор:**

* `Climate(double temperature, double humidity)`:  Создает новый объект `Climate`  с заданными температурой и влажностью. 

**Методы:**

* `toString()`:  Возвращает строковое представление объекта `Climate`.
* `getHumidity()`:  Возвращает значение влажности.
* `getTemperature()`:  Возвращает значение температуры.

---

### Класс `EcoSystem`

**Описание:**

Класс `EcoSystem`  представляет экосистему,  содержащую животных,  растения,  ресурсы и климатические условия.

**Поля:**

* `animals`:  Список животных в экосистеме.
* `plants`:  Список растений в экосистеме.
* `resources`:  Список ресурсов в экосистеме.
* `climate`:  Климатические условия в экосистеме.
* `environment`:  Среда экосистемы.

**Конструктор:**

* `EcoSystem()`:  Создает новый объект `EcoSystem`.

**Методы:**

* `clearSimulation()`:  Очищает данные симуляции,  удаляя все животные,  растения,  ресурсы и климатические условия. 
* `createNewSimulation()`:  Создает новую симуляцию,  заполняя экосистему случайными животными,  растениями и ресурсами.
* `setCustomConditions(double temperature, double humidity)`:  Устанавливает заданные климатические условия.
* `simulateOneDay()`:  Симулирует один день в экосистеме. 
* `applyNaturalMortality()`:  (Приватный метод)  Применяет естественную смертность к животным и растениям.
* `addAnimal(Animal animal)`:  Добавляет животное в экосистему.
* `removeAnimal(String animalName)`:  Удаляет животное из экосистемы по имени.
* `addPlant(Plant plant)`:  Добавляет растение в экосистему.
* `removePlant(String plantName)`:  Удаляет растение из экосистемы по имени.
* `addResource(Resource resource)`:  Добавляет ресурс в экосистему.
* `removeResource(Resource.ResourceType resourceType)`:  Удаляет ресурс из экосистемы по типу. 
* `showEcosystem()`:  Выводит информацию о текущем состоянии экосистемы.
* `toString()`:  Возвращает строковое представление объекта `EcoSystem`.
* `getClimate()`:  Возвращает климатические условия. 
* `getAnimals()`:  Возвращает список животных.
* `getPlants()`:  Возвращает список растений.
* `getResources()`:  Возвращает список ресурсов.

---

### Класс `Environment`

**Описание:**

Класс `Environment` представляет среду экосистемы, 
включая такие параметры, как температура, влажность, 
уровень воды, минералов и солнечного света.

**Поля:**

* `temperature`: Температура (число с плавающей точкой).
* `humidity`: Влажность (число с плавающей точкой).
* `water`: Уровень воды (число с плавающей точкой).
* `minerals`: Уровень минералов (число с плавающей точкой).
* `sunlight`: Уровень солнечного света (число с плавающей точкой).
* `random`: Генератор случайных чисел (объект `Random`).
* `dayOfYear`: День года (целое число).

**Константы:**

* `OPTIMAL_WATER`: Оптимальный уровень воды.
* `OPTIMAL_MINERALS`: Оптимальный уровень минералов.
* `OPTIMAL_SUNLIGHT`: Оптимальный уровень солнечного света.
* `OPTIMAL_HUMIDITY`: Оптимальная влажность.
* `TEMPERATURE_STRESS_LOW`: Нижний порог температуры, вызывающий стресс.
* `TEMPERATURE_STRESS_HIGH`: Верхний порог температуры, вызывающий стресс.

**Конструктор:**

* `Environment()`: Создает новый объект `Environment` с 
  установленными значениями по умолчанию.

**Методы:**

* `update(List<Animal> animals, List<Plant> plants)`: 
  Обновляет состояние среды, включая потребление ресурсов 
  растениями, изменение климата, пополнение ресурсов, 
  возможное выпадение осадков и расчет уровня солнечного света.

* `applyRainEvent()`: (Приватный метод) Симулирует выпадение осадков, 
  увеличивая уровень воды и минералов.

* `consumeResources(List<Plant> plants)`: (Приватный метод) 
  Растения потребляют воду и минералы из среды.

* `calculateSunlight()`: (Приватный метод) Рассчитывает уровень 
  солнечного света в зависимости от дня года.

* `growPlants(List<Plant> plants)`: Симулирует рост растений, 
  учитывая факторы окружающей среды.

* `calculateGrowthFactor(Environment environment, Plant plant)`: 
  (Приватный метод) Вычисляет фактор роста растения на основе 
  уровня воды, минералов, солнечного света, температуры и влажности.

* `calculateTemperatureFactor(double currentTemperature)`: 
  (Приватный метод) Вычисляет фактор влияния температуры 
  на рост растений.

* `replenishResources()`: Пополняет уровень воды и минералов 
  в среде.

* `applyClimateChange()`: Симулирует изменение климата, 
  изменяя температуру и влажность в зависимости от времени года.

* `setResourcesFromList(List<Resource> resources)`: 
  Устанавливает уровень ресурсов на основе списка объектов `Resource`.

* `calculateFactor(double currentValue, double optimalValue)`: 
  (Приватный method) Вычисляет фактор влияния текущего значения 
  ресурса на рост растений по отношению к оптимальному значению.

* `toString()`: Возвращает строковое представление объекта `Environment`.

* `getHumidity()`: Возвращает значение влажности.

* `setMinerals(double minerals)`: Устанавливает уровень минералов.

* `setSunlight(double sunlight)`: Устанавливает уровень солнечного света.

* `setHumidity(double humidity)`: Устанавливает значение влажности.

* `getTemperature()`: Возвращает значение температуры.

* `setTemperature(double temperature)`: Устанавливает значение температуры.

* `getSunlight()`: Возвращает уровень солнечного света.

* `setWater(double water)`: Устанавливает уровень воды.

* `getMinerals()`: Возвращает уровень минералов.

* `getWater()`: Возвращает уровень воды.

* `getDayOfYear()`: Возвращает день года.


---

### Класс `Plant`

**Описание:**

Класс `Plant` представляет растение в симулируемой экосистеме.

**Поля:**

* `name`: Название растения (строка).
* `quantity`: Количество растений (целое число).
* `growthRate`: Коэффициент роста (число с плавающей точкой).
* `waterNeed`: Потребность в воде (число с плавающей точкой).
* `mineralsNeed`: Потребность в минералах (число с плавающей точкой).

**Конструктор:**

* `Plant(String name, int quantity, double growthRate, double waterNeed, double mineralsNeed)`: Создает новый объект `Plant` с заданными параметрами.

**Методы:**

* `getName()`: Возвращает имя растения.
* `getQuantity()`: Возвращает количество растений.
* `setQuantity(int quantity)`: Устанавливает количество растений.
* `setGrowthRate(double growthRate)`: Устанавливает коэффициент роста.
* `getWaterNeed()`: Возвращает потребность в воде.
* `setWaterNeed(double waterNeed)`: Устанавливает потребность в воде.
* `getMineralsNeed()`: Возвращает потребность в минералах.
* `setMineralsNeed(double mineralsNeed)`: Устанавливает потребность в минералах.
* `grow(double waterFactor, double mineralFactor, double sunlightFactor)`: Симулирует рост растения с учетом факторов воды, минералов и солнечного света.
* `applyNaturalMortality(double deathRate)`: Применяет естественную смертность к количеству растений.
* `toString()`: Возвращает строковое представление объекта `Plant`.

---

### Класс `Resource`

**Описание:**

Класс `Resource` представляет ресурс в экосистеме, 
такой как вода, минералы или солнечный свет.

**Поля:**

* `type`: Тип ресурса (значение из перечисления `ResourceType`).
* `amount`: Количество ресурса (число с плавающей точкой).

**Перечисление `ResourceType`:**

* `WATER`: Вода.
* `MINERALS`: Минералы.
* `SUNLIGHT`: Солнечный свет.

**Конструктор:**

* `Resource(ResourceType type, double amount)`: 
  Создает новый объект `Resource` с заданным типом и количеством.

**Методы:**

* `getType()`: Возвращает тип ресурса.
* `getAmount()`: Возвращает количество ресурса.
* `setAmount(double amount)`: Устанавливает количество ресурса.
* `toString()`: Возвращает строковое представление объекта `Resource`.

---

### Класс `Simulation`

**Описание:**

Класс `Simulation` управляет симуляцией экосистемы, 
включая создание, загрузку, сохранение и выполнение симуляции.

**Поля:**

* `ecosystem`: Объект `EcoSystem`, представляющий симулируемую экосистему.
* `repository`: Объект `EcosystemRepository`, 
  отвечающий за сохранение и загрузку симуляций.
* `predictionEngine`: Объект `PredictionEngine`, 
  используемый для прогнозирования изменений в экосистеме.
* `simulationDay`: Текущий день симуляции (целое число).

**Конструктор:**

* `Simulation(String dataDirectory)`: Создает новый объект `Simulation` 
  с заданной директорией для хранения данных симуляции.

**Методы:**

* `getPredictionEngine()`: Возвращает объект `PredictionEngine`.
* `createNewSimulation()`: Создает новую симуляцию экосистемы.
* `loadSimulation(String filename)`: Загружает симуляцию из файла.
* `saveSimulation()`: Сохраняет текущую симуляцию в файл.
* `simulateOneDay()`: Выполняет симуляцию одного дня в экосистеме.
* `showEcosystem()`: Выводит информацию о текущем состоянии экосистемы.
* `getEcosystem()`: Возвращает объект `EcoSystem`.
* `getRepository()`: Возвращает объект `EcosystemRepository`.

---

### Класс `FileHandler`

**Описание:**

Класс `FileHandler` отвечает за загрузку 
данных о животных, растениях и ресурсах из файлов.

**Методы:**

* `loadAnimals(String filename)`: Загружает данные о животных 
  из файла с заданным именем.
* `loadPlants(String filename)`: Загружает данные о растениях 
  из файла с заданным именем.
* `loadResources(String filename)`: Загружает данные о ресурсах 
  из файла с заданным именем.

---

### Класс `Logger`

**Описание:**

Класс `Logger` отвечает за ведение журнала 
симуляции, записывая информацию о событиях 
и состоянии экосистемы в файл.

**Поля:**

* `logFile`: Имя файла журнала.
* `currentLogLevel`: Текущий уровень логирования 
  (значение из перечисления `LogLevel`).

**Перечисление `LogLevel`:**

* `DEBUG`: Отладочные сообщения.
* `INFO`: Информационные сообщения.
* `WARN`: Предупреждения.
* `ERROR`: Ошибки.

**Конструктор:**

* `Logger(String logFile)`: Создает новый объект `Logger` 
  с заданным именем файла журнала.

**Методы:**

* `setLogLevel(LogLevel logLevel)`: Устанавливает уровень логирования.
* `debug(String message)`: Записывает отладочное сообщение в журнал.
* `info(String message)`: Записывает информационное сообщение в журнал.
* `warn(String message)`: Записывает предупреждение в журнал.
* `error(String message)`: Записывает сообщение об ошибке в журнал.
* `logDay(int day)`: Записывает информацию о текущем дне симуляции.
* `logEcosystem(EcoSystem ecosystem)`: Записывает информацию о 
  состоянии экосистемы.

---

### Класс `PredictionEngine`

**Описание:**

Класс `PredictionEngine` используется для 
прогнозирования изменений в экосистеме.

**Методы:**

* `predictPopulationChanges(EcoSystem ecosystem)`: 
  Генерирует прогноз изменений популяции животных и растений 
  в экосистеме.
* `predictAnimalPopulation(Animal animal, EcoSystem ecosystem)`: 
  (Приватный метод)  Прогнозирует изменение 
  популяции конкретного вида животных.
* `predictPlantGrowth(Plant plant, EcoSystem ecosystem)`: 
  (Приватный метод)  Прогнозирует рост 
  конкретного вида растений.
* `predictClimateTrends(EcoSystem ecosystem)`: 
  (Приватный метод) Прогнозирует изменения 
  климата в экосистеме.
* `checkFoodAvailability(Animal animal, EcoSystem ecosystem)`: 
  (Приватный метод) Проверяет наличие пищи 
  для конкретного вида животных.
* `calculatePlantGrowthFactor(Plant plant, Resource resource, double optimalValue)`: 
  (Приватный метод) Вычисляет фактор роста 
  растений на основе доступности 
  конкретного ресурса.





>>>>>>> 57bd10cc4f4956cb16705ce9865194527cf4fea9

;Опишите функцию, лениво возвращающий минимальный элемент
;внутри плавающего окна в бесконечной числовой
;последовательности. Оптимизируйте производительность."

;Создаю функцию, принимающую предикат и последовательность
;Использую let, так как он лучше var:))
;Разделяю последовательность и возвращаю вектор
(defn split-seq [pred seq]
  (let [satisfying (filter pred seq)
        unsatisfying (remove pred seq)]
    [satisfying unsatisfying]))

;Для примера задаю ленивую последовательность
(def numbers (range 1 11))

;Получается, пересоздал существующую функцию (зато сам, все для примера)
(defn is-even? [x] (even? x))

;Снова let, вывожу результаты
(let [[even-nums odd-nums] (split-seq is-even? numbers)]
  (println "Четные числа:" even-nums)
  (println "Нечетные числа:" odd-nums))

; Напишите функцию, которая принимает на вход канал состоящий из последовательности чисел,
; первое из которых является количеством последующих элементов, которые нужно поместить в массив,
; а за ней следуют элементы этого массива, и возвращающая отдельные массивы.
; Например 3, 4, 0, 2, 1, 2, 2, 4, 5 будет превращено в [4, 0, 2], [2], [4, 5]

(defn split-channel [channel]
  (loop [remaining (seq channel) result []]
    (if (empty? remaining)
      result
      (let [count-to-take (first remaining)
            new-remaining (next remaining)
            sub-array (take count-to-take new-remaining)]
        (recur (drop count-to-take new-remaining) (conj result sub-array))))))

(def input-channel [5 1 2 3 4 5 3 1 2 3 2 1 2 1 1 6 1 2 3 4 5 6])
(def result (split-channel input-channel))
(println result)

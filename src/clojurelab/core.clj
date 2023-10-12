(require '[clojure.core.async :as async])

(defn process-channel [input-chan result-chan]
  (async/go
    (let [data (async/<! input-chan)]
      (let [data-array (clojure.string/split data #"\s+")]
        (let [data-as-integers (mapv #(Integer. %) data-array)]
          (loop [remaining data-as-integers result []]
            (if (empty? remaining)
              (do
                (println "In function:" result)
                (async/>! result-chan result)
                nil)
              (let [count-to-take (first remaining)
                    new-remaining (next remaining)
                    sub-array (take count-to-take new-remaining)]
                (recur (drop count-to-take new-remaining) (conj result sub-array)))))
          )))))


(def input-chan (async/chan))
(def result-chan (async/chan))

(async/go
  (async/>! input-chan "5 1 2 3 4 5 3 1 2 3 2 1 2 1 1 6 1 2 3 4 5 6"))

(process-channel input-chan result-chan)

(async/timeout 1000)

(async/go
  (let [result (async/<! result-chan)]
    (println "Received from channel:" result)))


(async/close! input-chan)
(async/close! result-chan)

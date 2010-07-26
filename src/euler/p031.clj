(ns euler.p031)

(defn ways [n parts]
  (letfn [(count-ways [n max-part-index]
                      (let [part (nth parts max-part-index)]
                        (cond (> part n) 0
                              (= part n) 1
                              :else (+ (count-ways (- n part) max-part-index)
                                       (count-ways n (inc max-part-index))))))]
    (count-ways n 0)))

(defn solution []
  (ways 200 [1 2 5 10 20 50 100 200]))


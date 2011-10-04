(ns dma.euler.p074
  (:use dma.euler.numeric))

(def digit-fact (vec (map factorial (range 10))))

(defn sum-digit-fact [n]
  (sum (map digit-fact (digits n))))

(def chain-length
     (let [chains (atom {})]
       (letfn [(update-lengths [chain length]
                  (when (seq chain)
                    (let [new-chains (apply assoc @chains (interleave chain (repeat length)))]
                      (reset! chains new-chains)))
                  length)
               (chain-length [n chain]
                  (if-let [n-length (@chains n)]
                    (update-lengths chain (+ n-length (count chain)))
                    (if (chain n)
                      (update-lengths chain (count chain))
                      (recur (sum-digit-fact n) (conj chain n)))))]
         (fn [n] (chain-length n #{})))))

(defn solution {:answer 402} []
  (count (filter #(= % 60) (map chain-length (range 1000000)))))

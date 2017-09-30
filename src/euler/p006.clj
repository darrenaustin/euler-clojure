(ns euler.p006
  (:use euler.numeric))

(defn solution {:answer 25164150} []
  (let [ns (take 100 (natural-nums))]
    (- (square (sum ns))
       (sum (map square ns)))))


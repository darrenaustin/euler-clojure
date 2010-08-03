(ns dma.euler.p028)

;; Starting at corner 3, we walk around the outside corners:
;;   corner_diff = c - 1
;;   upper_right = c * c
;;   upper_left = upper_right - corner_diff
;;   lower_left = upper_left - corner_diff
;;   lower_right = lower_left - corner_diff
;;   sum += upper_right + upper_left + lower_left + lower_right
;;
;; or:
;;   sum += 4 * c *c - 6 * c + 6
;;
;; Finally we need to add one for the very center
;;
(defn solution {:answer 669171001} []
  (reduce + 1 (map #(+ (* 4 % %) (* -6 %) 6) (range 3 1002 2))))

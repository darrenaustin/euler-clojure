(ns dma.euler.p019
  (:use dma.euler.numeric)
  (:import (java.util GregorianCalendar)))

;; Kind of cheap using the built in calendar class, but it is clean :)
(defn solution {:answer 171} []
  (sum
   (for [year (range 1901 2001) month (range 1 13)]
     (let [calendar (doto (GregorianCalendar.) (.set year month 1))]
       (if (= (.get calendar GregorianCalendar/DAY_OF_WEEK)
              GregorianCalendar/SUNDAY)
         1 0)))))


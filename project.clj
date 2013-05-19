(defproject imperio "0.8.0"
  :description "Control your computer with your smartphone, without the hassle"
  :url "http://imper.io"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [http-kit "2.1.1"]
                 [compojure "1.1.5"]
                 [cheshire "5.1.1"]]
  :main imperio.main)

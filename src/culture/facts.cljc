(ns culture.facts
  "Regional-culture catalog for Naples (Comune di Napoli) -- local dishes,
  protected products, beverages, festivals and heritage sites, piggybacked
  onto this municipality compliance repo per ADR-2607171400
  (cloud-itonami-municipality-culture-catalog, in com-junkawasaki/root),
  sibling namespace to `ordinance.facts` (ADR-2607141700).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "municipality-slug -> vector of culture entries."
  {"napoli"
   [{:culture/id "napoli.dish.pizza-napoletana"
     :culture/name "Neapolitan pizza"
     :culture/name-local "Pizza napoletana"
     :culture/municipality "napoli"
     :culture/country "ITA"
     :culture/kind :dish
     :culture/summary "Pizza style originating in Naples, in the strictest tradition limited to two variations (Margherita and marinara); an EU Traditional Speciality Guaranteed product since 2010, and the art of the Neapolitan pizzaiuolo was inscribed on UNESCO's Intangible Cultural Heritage list in 2017."
     :culture/url "https://en.wikipedia.org/wiki/Neapolitan_pizza"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "napoli.dish.spaghetti-alle-vongole"
     :culture/name "Spaghetti alle vongole"
     :culture/municipality "napoli"
     :culture/country "ITA"
     :culture/kind :dish
     :culture/summary "Pasta dish of spaghetti cooked with fresh clams, garlic, parsley and olive oil, part of Neapolitan cuisine."
     :culture/url "https://en.wikipedia.org/wiki/Spaghetti_alle_vongole"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "napoli.dish.ragu-napoletano"
     :culture/name "Neapolitan ragù"
     :culture/name-local "Ragù napoletano"
     :culture/municipality "napoli"
     :culture/country "ITA"
     :culture/kind :dish
     :culture/summary "Ragù associated with the city of Naples, made by braising whole cuts of meat for several hours in tomato purée and sauce, traditionally tied to Sunday family lunches."
     :culture/url "https://en.wikipedia.org/wiki/Neapolitan_rag%C3%B9"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "napoli.dish.sfogliatella"
     :culture/name "Sfogliatella"
     :culture/municipality "napoli"
     :culture/country "ITA"
     :culture/kind :dish
     :culture/summary "Shell-shaped filled pastry originating in Campania, with two main Neapolitan variants (riccia and frolla); sold in Naples since 1818."
     :culture/url "https://en.wikipedia.org/wiki/Sfogliatella"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "napoli.dish.baba"
     :culture/name "Rum baba"
     :culture/name-local "Babà"
     :culture/municipality "napoli"
     :culture/country "ITA"
     :culture/kind :dish
     :culture/summary "Small yeast cake saturated in rum syrup; popular in Naples, where it became a Neapolitan specialty under the name babà."
     :culture/url "https://en.wikipedia.org/wiki/Rum_baba"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "napoli.product.mozzarella-di-bufala-campana"
     :culture/name "Mozzarella di bufala campana"
     :culture/municipality "napoli"
     :culture/country "ITA"
     :culture/kind :product
     :culture/summary "Fresh pasta filata cheese made from Italian Mediterranean buffalo milk, traditionally produced in Campania; EU protected designation of origin (PDO) since 1996."
     :culture/url "https://en.wikipedia.org/wiki/Mozzarella_di_bufala_campana"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "napoli.beverage.limoncello"
     :culture/name "Limoncello"
     :culture/municipality "napoli"
     :culture/country "ITA"
     :culture/kind :beverage
     :culture/summary "Traditional lemon liqueur produced mainly in southern Italy, especially around the Gulf of Naples and the Gulf of Salerno."
     :culture/url "https://en.wikipedia.org/wiki/Limoncello"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "napoli.festival.festa-di-piedigrotta"
     :culture/name "Festa di Piedigrotta"
     :culture/municipality "napoli"
     :culture/country "ITA"
     :culture/kind :festival
     :culture/summary "Popular festival held on 8 September in the Piedigrotta area of Naples, centred on Neapolitan song and music; its modern musical festival form was inaugurated in 1839."
     :culture/url "https://it.wikipedia.org/wiki/Festa_di_Piedigrotta"
     :culture/url-provenance :wikipedia-it
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "napoli.heritage.centro-storico"
     :culture/name "Historic Centre of Naples"
     :culture/name-local "Centro storico di Napoli"
     :culture/municipality "napoli"
     :culture/country "ITA"
     :culture/kind :heritage
     :culture/summary "Naples' historic city centre, a UNESCO World Heritage Site designated in 1995, whose present-day street layout still follows the ancient Greek grid."
     :culture/url "https://en.wikipedia.org/wiki/Naples"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [muni] (get catalog muni))

(defn coverage
  ([] (coverage (keys catalog)))
  ([munis]
   (let [have (filter catalog munis)
         missing (remove catalog munis)]
     {:requested (count munis)
      :covered (count have)
      :covered-municipalities (vec (sort have))
      :missing-municipalities (vec (sort missing))
      :note (str "cloud-itonami-municipality-ita-napoli culture catalog "
                 "(ADR-2607171400): " (count (get catalog "napoli"))
                 " Napoli entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [muni kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis muni)))

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib prefix="ctg" uri="customtags" %> 
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="description" content="Order music web application">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="keywords" content="music order,trance,EDM,electronic dance music,electronic music,DJ,house music,music">
  <meta name="author" content="Maxim Gadalov">
  <title>Main page</title>
  <link rel="stylesheet" href="CSS/normalize.css">
  <link rel="stylesheet" href="CSS/screen.css">
  <link rel="stylesheet" href="CSS/content.css">
</head>
<body>
<header>
    <ctg:header-custom nickname="${nickname}" role="${role}"/> 
  </header>
  <h1>Welcome to the EDM web service</h1>
 <section class="content"> 
  <div class="main-div">
   <img class="edm" src="IMG/EDM_music.jpg" onclick="hidetxt('span1'); return false;" alt="EDM" title="click for more info">
   <span id="span1" style="display:none;">
   Electronic dance music (also known as EDM, dance music, club music or simply dance) is a 
   broad range of percussive electronic music genres produced largely for nightclubs, raves and festivals. 
   Produced for playback by disc jockeys (DJs), EDM is often used in the context of a live mix, where a DJ creates a 
   seamless selection of tracks by segueing from one recording to the next.
   EDM gained popularity in Europe before the United States due to its appeal to the European club scene.
   By the early 2010s the term "electronic dance music" and the initialism "EDM" was being pushed by the U.S. music 
   industry and music press in what was largely an effort to re-brand U.S. rave culture. In the UK, "dance music" or "dance"
   are more common terms for the genre. In this context, EDM does not refer to a specific genre, but serves as an umbrella
   term for several commercially popular genres, including techno, house, trance, drum and bass, dubstep, Jersey club, and 
   their respective subgenres.
   </span>
  </div><br>
  <h2>EDM genres :</h2>
  <div class="about-trance">
  <img class="trance" src="IMG/trance_music_needcut.jpg" onclick="hidetxt('span2'); return false;" alt="trance" title="click for more info">
  <span id="span2" style="display:none;">
  Trance is a genre of electronic dance music that developed during the 1990s in Germany. 
  It is characterized by a tempo lying between 125 and 150 beats per minute (BPM), repeating melodic phrases,
  and a musical form that distinctly builds tension and elements throughout a track often culminating in 1 to
  2 "peaks" or "drops." Although trance is a genre of its own, it liberally incorporates influences from other 
  musical styles such as techno, house, pop, chill-out, classical music, tech house, ambient, and film music.
  A trance refers to a state of hypnotism and heightened consciousness. This is portrayed in trance music by 
  the mixing of layers with distinctly foreshadowed build-up and release. A characteristic of virtually all trance 
  music is a mid-song climax followed by a soft breakdown disposing of beats and percussion entirely, and 
  leaving the melody and/or atmospherics to stand alone for an extended period before gradually building up again. 
  As a result, trance tracks are often lengthy to allow for this progression and have sufficiently sparse opening 
  and closing sections to facilitate mixing by DJs.
  Trance can be purely instrumental, although vocals are also a common feature. Typically they are performed
  by mezzo-soprano to soprano female soloists, often without verse/chorus structure. Structured vocal form 
  in trance music forms the basis of the vocal trance subgenre, which has been described as "grand, soaring, 
  and operatic" and "ethereal female leads floating amongst the synths".
  </span>
  </div>
  <div class="main-div">
  <img class="house" src="IMG/music_house.jpg" onclick="hidetxt('span3'); return false;" alt="house" title="click for more info">
  <span style="display:none;" id="span3">
  House music is a genre of electronic dance music created by club DJs and music producers that originated in Chicago 
  in the early 1980s. Early house music was generally dance-based music characterized by repetitive 4/4 beats, 
  rhythms mainly provided by drum machines, off-beat hi-hat cymbals, and synthesized basslines. While house 
  displayed several characteristics similar to disco music, it was more electronic and minimalistic, and the repetitive 
  rhythm of house was more important than the song itself. House music initially became popular in Chicago clubs in 1984,
  pioneered by figures such as Frankie Knuckles, Phuture, and Mr. Fingers, and was associated with African-American 
  and gay subcultures. House music quickly spread to other American cities such as Detroit, New York City, Baltimore, 
  and Newark – all of which developed their own regional scenes. In the mid-to-late 1980s, house music became popular in 
  Europe as well as major cities in South America, and Australia.
  Early house music commercial success in Europe saw songs such as "Pump Up The Volume" by MARRS (1987), "House Nation" 
  by House Master Boyz and the Rude Boy of House (1987), "Theme from S'Express" by S'Express (1988) and "Doctorin' the 
  House" by Coldcut (1988) in the pop charts. Since the early to mid-1990s, house music has been infused in mainstream
  pop and dance music worldwide. In the late 1980s, many local Chicago house music artists suddenly found themselves 
  presented with major label deals. House music proved to be a commercially successful genre and a more mainstream 
  pop-based variation grew increasingly popular. House music in the 2010s, while keeping several of these core elements, 
  notably the prominent kick drum on every beat, varies widely in style and influence, ranging from the soulful and 
  atmospheric deep house to the more minimalistic microhouse. House music has also fused with several other genres creating 
  fusion subgenres, such as euro house, tech house, electro house and jump house.
  Artists and groups such as Madonna, Janet Jackson, Paula Abdul, Aretha Franklin, Bananarama, Diana Ross, Tina Turner, 
  Whitney Houston, Steps, Kylie Minogue, Björk, and C+C Music Factory all incorporated the genre into their work in the 
  1990s and beyond. After enjoying significant success in the early to mid-90s, house music grew even larger during the 
  second wave of progressive house (1999–2001). The genre has remained popular and fused into other popular subgenres, 
  for example, ghetto house, deep house and tech house. As of 2016, house music remains popular in both clubs and in 
  the mainstream pop scene while retaining a foothold on underground scenes across the globe.
  </span>
  </div>
  <div class="main-div">
  <img class="drum-and-bass" src="IMG/drum&bass.jpg" onclick="hidetxt('span4'); return false;" alt="drum%bass" title="click for more info">
  <span style="display:none;" id="span4">
  Drum and bass (also written as "drum 'n' bass", "drum & bass" and commonly abbreviated as "D&B", "DnB" or "D'n'B") is 
  a genre and branch of electronic music which emerged from rave and oldschool jungle scenes in England during the early 1990s.
  The style is often characterized by fast breakbeats (typically between 150–180 beats per minute) with heavy bass and sub-bass lines,
  sampled sources, and synthesizers.
  The popularity of drum and bass at its commercial peak ran parallel to several other homegrown dance styles in the UK including big
  beat and hard house. Drum and bass incorporates a number of scenes and styles. A major influence on jungle and drum and bass was the 
  original Jamaican dub and reggae sound. Another feature of the style is the complex syncopation of the drum tracks' breakbeat.
  Drum and bass subgenres include breakcore, ragga jungle, hardstep, darkstep, techstep, neurofunk, ambient drum and bass, liquid funk, 
  deep, drumfunk, funkstep, sambass, dnbnoise, and drill 'n' bass. From its roots in the UK, the style has established itself around 
  the world. Drum and bass has influenced many other genres like hip hop, big beat, dubstep, house, trip hop, ambient music, techno, 
  rock and pop. Drum and bass is dominated by a relatively small group of record labels. The major international music labels have 
  shown very little interest in the drum and bass scene. Drum and bass remains most popular in the UK although it has developed scenes 
  all around the world, in countries such as the United States, the Netherlands, Belgium, New Zealand, Canada, Austria, and Australia.
  </span>
  </div>
  <div class="main-div">
  <img class="dubstep" src="IMG/dubstep.jpg" onclick="hidetxt('span5'); return false;" alt="dubstep" title="click for more info">
  <span style="display:none;" id="span5">
  Dubstep is a genre of electronic dance music that originated in South London, England. It emerged in the late 1990s as a development 
  within a lineage of related styles such as 2-step garage, dub, techno, drum and bass, broken beat, jungle, and reggae. In the UK the 
  origins of the genre can be traced back to the growth of the Jamaican sound system party scene in the early 1980s. The music generally 
  features sparse, syncopated drum and percussion patterns with bass lines that contain prominent sub bass frequencies.
  The earliest dubstep releases date back to 1998, and were usually featured as B-sides of 2-step garage single releases. These tracks 
  were darker, more experimental remixes with less emphasis on vocals, and attempted to incorporate elements of breakbeat and drum and 
  bass into 2-step. In 2001, this and other strains of dark garage music began to be showcased and promoted at London's night club Plastic 
  People, at the "Forward" night (sometimes stylised as FWD>>), which went on to be considerably influential to the development of dubstep. 
  The term "dubstep" in reference to a genre of music began to be used by around 2002 by labels such as Big Apple, Ammunition, and Tempa,
  by which time stylistic trends used in creating these remixes started to become more noticeable and distinct from 2-step and grime.
  A very early supporter of the sound was BBC Radio 1 DJ John Peel, who started playing it from 2003 onwards. In 2004, the last year 
  of his show, his listeners voted Distance, Digital Mystikz, and Plastician in their top 50 for the year. Dubstep started to spread 
  beyond small local scenes in late 2005 and early 2006; many websites devoted to the genre appeared on the Internet and aided the 
  growth of the scene, such as dubstepforum, the download site Barefiles and blogs such as gutterbreakz. Simultaneously, the genre 
  was receiving extensive coverage in music magazines such as The Wire and online publications such as Pitchfork, with a regular 
  feature entitled The Month In: Grime/Dubstep. Interest in dubstep grew significantly after BBC Radio 1 DJ Mary Anne Hobbs started
  championing the genre, beginning with a show devoted to it (entitled "Dubstep Warz") in January 2006.
  </span>
  </div>
  <div class="main-div">
  <img class="techno" src="IMG/techno.jpg" onclick="hidetxt('span6'); return false;" alt="techno" title="click for more info">
  <span style="display:none;" id="span6">
  Techno is a form of electronic dance music that emerged in Detroit, Michigan, in the United States during the mid-to-late 1980s.
  The first recorded use of the word techno in reference to a specific genre of music was in 1988. Many styles of techno now exist, 
  but Detroit techno is seen as the foundation upon which a number of subgenres have been built.
  In Detroit, techno resulted from the melding of African American music including Chicago house, funk, electro, and electric jazz 
  with electronic music by artists such as Kraftwerk, Giorgio Moroder, and Yellow Magic Orchestra. Added to this is the influence 
  of futuristic and fictional themes relevant to life in American late capitalist society, with Alvin Toffler's book The Third 
  Wave being a notable point of reference. Pioneering producer Juan Atkins cites Toffler's phrase "techno rebels" as inspiring 
  him to use the word techno to describe the musical style he helped to create. This unique blend of influences aligns techno 
  with the aesthetic referred to as afrofuturism. To producers such as Derrick May, the transference of spirit from the body to 
  the machine is often a central preoccupation; essentially an expression of technological spirituality. In this manner: 
  "techno dance music defeats what Adorno saw as the alienating effect of mechanisation on the modern consciousness".
  Stylistically, techno is generally repetitive instrumental music, oftentimes produced for use in a continuous DJ set. 
  The central rhythmic component is most often in common time (4/4), where time is marked with a bass drum on each quarter 
  note pulse, a backbeat played by snare or clap on the second and fourth pulses of the bar, and an open hi-hat sounding every 
  second eighth note. The tempo tends to vary between approximately 120 to 150 beats per minute (bpm), depending on the style 
  of techno. The creative use of music production technology, such as drum machines, synthesizers, and digital audio workstations, 
  is viewed as an important aspect of the music's aesthetic. Many producers use retro electronic musical devices to create what 
  they consider to be an authentic techno sound. Drum machines from the 1980s such as Roland's TR-808 and TR-909 are highly prized, 
  and software emulations of such retro technology are popular among techno producers.
  Music journalists and fans of techno are generally selective in their use of the term; so a clear distinction can be made between
  sometimes related but often qualitatively different styles, such as tech house and trance.
  </span>
  </div>
  </section>
  <aside class="second-content">
  <h2>Internationally known DJ's</h2>
  <div class="about-dj">
  <div class="dj-name" onclick="showSecondContent('div1','pointer1'); return false;">
  Tiesto
  <img class="pointer"  id="pointer1" src="IMG/pointer.png" alt="pointer">
  </div>
  <div id="div1" style="display:none;">
  <img class="dj" src="IMG/tiesto.jpg" alt="Tiesto">
  Tijs Michiel Verwest, better known by his stage name Tiësto, is a Dutch DJ and 
  record producer. He is named "the Greatest DJ of All Time" by MixMag magazine in a poll voted by the fans.
  In 1997, he founded the label Black Hole Recordings with Arny Bink, where he released the Magik and In Search of Sunrise CD series. 
  Tiësto met producer Dennis Waakop Reijers in 1998; the two have worked together extensively since then.
  In 1999 and 2000, Tiësto collaborated with Ferry Corsten to create Gouryella. His 2000 remix of Delerium's "Silence" featuring Sarah 
  McLachlan exposed him to more mainstream audiences. In 2001, he released his first solo album, In My Memory. The title track is a remix 
  of an original song written by and featuring Nicola Hitchcock. This album gave him several major hits that launched his career. 
  He was voted World No. 1 DJ by DJ Magazine in its annual Top 100 DJs readership poll consecutively for three years from 2002–04.
  Just after releasing his second studio album Just Be he performed live at the 2004 Summer Olympics opening ceremony in Athens, the 
  first DJ to play live on stage at an Olympics. In April 2007 Tiësto launched his radio show Tiësto's Club Life on Radio 538 in 
  the Netherlands and released his third studio album Elements of Life. The album reached number one on the Belgian album chart as well 
  on "Billboard Top Electronic Albums" in the U.S. and received a nomination for a Grammy Award in 2008. Tiësto released his fourth 
  studio album Kaleidoscope in October 2009, followed by "A Town Called Paradise" in 2014. He won the Grammy Award for Best Remixed 
  Recording, Non-Classical for his remixed version of John Legend's hit "All Of Me" at the 57th Annual Grammy Awards.
  </div>
  </div>
  <div class="about-dj">
  <div class="dj-name" onclick="showSecondContent('div2','pointer2'); return false;">
  Armin Van Buuren
  <img class="pointer"  id="pointer2" src="IMG/pointer.png" alt="pointer">
  </div>
  <div id="div2" style="display:none;">
  <img class="dj" src="IMG/armin.jpg" alt="Armin Van Buuren">
  Armin van Buuren is a Dutch DJ, record producer, musician, remixer, pianist and songwriter.
  Since 2001, Van Buuren has hosted a weekly radio show called A State of Trance, which currently is broadcast to more than 37 million weekly 
  listeners in 84 countries on over 100 FM radio stations. According to Djs And Festivals, "the radio show propelled him to stardom and helped 
  cultivate an interest in trance music around the world."
  Van Buuren has won a number of accolades. He has been ranked the No. 1 DJ by DJ Mag a record of five times, five years in a row. He is currently 
  ranked No. 4 on DJ Mag list for 2016. In 2014, he was nominated for a Grammy Award for Best Dance Recording for his single "This Is What It Feels Like"
  featuring Trevor Guthrie, which makes him the fourth trance artist ever to receive a Grammy Award nomination. In the United States, he holds the 
  record for most entries, twenty-one, on the Billboard Dance/Electronic Albums chart. His 2008 studio album, Imagine, entered the Dutch album chart 
  at No. 1, a first for a trance artist in Dutch music history.
  </div>
  </div>
  <div class="about-dj">
   <div class="dj-name" onclick="showSecondContent('div3','pointer3'); return false;">
  Deadmau5
  <img class="pointer"  id="pointer3" src="IMG/pointer.png" alt="pointer">
  </div>
  <div id="div3" style="display:none;">
  <img class="dj" src="IMG/deadmau5.jpg" alt="Deadmau5">
  Joel Thomas Zimmerman , known professionally as deadmau5 (pronounced "dead mouse"), is a Canadian electronic music producer and performer from Toronto, 
  Ontario. Zimmerman produces a variety of styles within the progressive house genre and sometimes other forms of electronic music. His tracks have been 
  included in numerous compilation albums, such as the 2007 In Search of Sunrise 6: Ibiza CD. The February 2008 issue of Mixmag's music magazine included 
  a free CD which was titled MixMag Presents: The Hottest New Name In Dance! DEADMAU5 Tech-Trance-Electro-Madness, mixed by Zimmerman. Tracks have also 
  been included and presented on Armin van Buuren's A State of Trance radio show. His debut album, Get Scraped, was released in 2005, followed by others 
  in the next few years.
  Zimmerman has received six Grammy Award nominations for his work. He has worked with other DJs and producers, such as Kaskade, MC Flipside, Rob Swire 
  and Wolfgang Gartner. On a number of releases, he has partnered with Melleefresh. An early 12" single produced on vinyl titled "I Don't Want No Other" 
  was released by Joel Zimmerman and Derek Caesar under the group name "Dred and Karma". A 2006 album titled deadmau5 Circa 1998-2002 was released using 
  the alias "Halcyon441". He has also collaborated with Steve Duda under the alias, "BSOD", as well as "WTF?" including Duda, Tommy Lee, and DJ Aero.
  Deadmau5 is currently one of the highest paid electronic music producers in the world.
  </div>
  </div>
  <div class="about-dj">
  <div class="dj-name" onclick="showSecondContent('div4','pointer4'); return false;">
  David Guetta
  <img class="pointer"  id="pointer4" src="IMG/pointer.png" alt="pointer">
  </div>
  <div id="div4" style="display:none;">
  <img class="dj" src="IMG/guetta.png" alt="David Guetta">
  Pierre David Guetta is a French DJ, record producer, remixer, and songwriter. He co-founded Gum Productions with Lisa Dodgson and released his first 
  album, Just a Little More Love, in 2002. Later, he released Guetta Blaster (2004) and Pop Life (2007).
  Guetta achieved mainstream success with his 2009 album One Love which included the hit singles "When Love Takes Over", "Gettin' Over You", "Sexy Bitch", 
  and "Memories", the first three of which reached number one in the United Kingdom. The 2011 follow-up album, Nothing but the Beat, continued this success,
  containing the hit singles "Where Them Girls At", "Little Bad Girl", "Without You", "Titanium", and "Turn Me On". He is among the first DJs to get into 
  the EDM scene and is known as the "Grandfather of EDM".
  Guetta has sold over nine million albums and 30 million singles worldwide. In 2011 Guetta was voted as the number one DJ in the 'DJ Mag Top 100 DJs' 
  fan poll.
  In 2013, Billboard crowned "When Love Takes Over" as the number one dance-pop collaboration of all time.
  </div>
  </div>
   <div class="about-dj">
  <div class="dj-name" onclick="showSecondContent('div5','pointer5'); return false;">
  Above & Beyond
  <img class="pointer"  id="pointer5" src="IMG/pointer.png" alt="pointer">
  </div>
  <div id="div5" style="display:none;">
  <img class="dj" src="IMG/above_and_beyond.jpg" alt="Above & Beyond">
  Above & Beyond is an English progressive trance group consisting of members Jono Grant, Tony McGuinness, and Paavo Siljamäki. 
  Formed in 2000, they are the owners of London-based electronic dance music labels Anjunabeats and Anjunadeep, and also host a 
  weekly radio show titled Group Therapy Radio. The trio has been consistently ranked among DJ Magazine's Top 100 DJs Poll, having placed at #6 in 2007,
  #4 in 2008 & 2009, #5 in 2010 & 2011, and most recently at #47 for 2016.
  </div>
  </div>
  <div class="about-dj">
   <div class="dj-name" onclick="showSecondContent('div6','pointer6'); return false;">
  Paul Van Dyk
  <img class="pointer"  id="pointer6" src="IMG/pointer.png" alt="pointer">
  </div>
   <div id="div6" style="display:none;">
  <img class="dj" src="IMG/paul-van-dyk.jpg" alt="Paul Van Dyk">
  Matthias Paul, better known by his stage name Paul van Dyk  is a German Grammy Award-winning DJ, record producer and musician. One of the first true 
  superstar DJs, Paul van Dyk was the first artist to receive a Grammy Award nomination in the newly added category of Best Dance/Electronic album 
  for his 2003 release Reflections. He was named the World's number one DJ in both 2005 and 2006, something only few DJs have ever achieved.
  He was the first ever DJ to be named number one by Mixmag in 2005. By 2008, he had sold over 3 million albums worldwide.
  A trance producer starting in the early 1990s, Paul quickly achieved popularity with his remix of "Love Stimulation" by Humate on the record label 
  MFS in 1993 and with his hit single "For an Angel" but, in recent times, he no longer likes to describe his music as trance, but rather simply as 
  electronic music.
  Paul van Dyk is currently the radio host of "Vonyc Sessions with Paul" on Dash Radio.
  </div>
  </div>
  <div class="about-dj">
   <div class="dj-name" onclick="showSecondContent('div7','pointer7'); return false;">
  Hardwell
  <img class="pointer"  id="pointer7" src="IMG/pointer.png" alt="pointer">
  </div>
  <div id="div7" style="display:none;">
  <img class="dj" src="IMG/hardwell.jpg" alt="Hardwell">
  Robbert van de Corput, who performs under the stage name Hardwell, is a Dutch big room house and electro house DJ, record producer and remixer.
  He was voted the World's No. 1 DJ on DJ Mag in 2013, and again in 2014. He is currently ranked at No. 3 on DJ Mag Top 100 DJs 2016 poll.
  Hardwell is best known for his sets at major music festivals such as Ultra Music Festival and Tomorrowland.
  Hardwell first gained recognition in 2009 for his bootleg of "Show Me Love vs. Be". He founded the record label Revealed Recordings in 2010
  and his own radio show and podcast Hardwell On Air in 2011. He has since released seven compilation albums through his label, as well as his 
  own documentary film. His debut studio album, United We Are, was released on 23 January 2015.
  </div>
  </div>
  </aside>
  <footer class="footer"> 
  EDM Portal<br>
  &copy; Maxim Gadalov, 2016<br>
  E-mail:gmaksim2012@gmail.com<br>
  Skype:gmaxim2111
  </footer>
  <script src="JS/mainpage.js"></script>
</body>
</html>
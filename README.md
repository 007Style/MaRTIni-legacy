# MaRTIni-legacy

### *The Lost Years: 1999 - 2001*

**Mobility and Research Testbed Initiative** - the part of the story that happens between "a quick AWT prototype" and "a Rust rewrite with pluggable protocols."

> This is a time capsule. It was dug up from a folder called `MaRTIni-DEV`, brushed off, and committed **exactly as found**: `.class` files, emacs autosaves, a stray `.DS_Store`, three copies of the same ZIP and all. Nothing was cleaned up, because the point of a time capsule is that you don't tidy it.

---

## Table of Contents

1. [The 30-second version](#the-30-second-version)
2. [Where this fits: v1, legacy, v2, v3](#where-this-fits-v1-legacy-v2-v3)
3. [The timeline](#the-timeline)
4. [What's in the box](#whats-in-the-box)
5. [Tour of the main app: `M.java` and its ten dialogs](#tour-of-the-main-app-mjava-and-its-ten-dialogs)
6. [The `layer/` package](#the-layer-package)
7. [Plot twist: ipKangaroo](#plot-twist-ipkangaroo)
8. [The dev1 - dev4 snapshots](#the-dev1---dev4-snapshots)
9. [Archaeology notes (the funny bits)](#archaeology-notes-the-funny-bits)
10. [Can I run it?](#can-i-run-it)
11. [Honest caveats](#honest-caveats)
12. [Credits](#credits)

---

## The 30-second version

- **v1** is the Aug 1999 prototype: a menu bar with ten items that don't open anything yet.
- **This repo** is what happened next. Those ten menu items grew into **ten real configuration dialogs** with about 85 labelled input fields between them, a **help window for each one**, an **About box** built on a small Swing layer-compositing library, a **Doodle** paint demo, and finally a **hard left turn** into a socket-based chat/talk client called **ipKangaroo**.
- **v2** is the Java 22 / Swing rewrite that cleaned up v1's ideas.
- **v3** is the Rust rewrite.

So this repo is the missing middle chapter. It's messy and a bit weird. It's also the only place a lot of this code still exists.

---

## Where this fits: v1, legacy, v2, v3

| | **v1** | **legacy** (this repo) | **v2** | **v3** |
|---|---|---|---|---|
| **Era** | Aug 1999 | Late 1999 - Feb 2001 | Modernized | Modernized again |
| **Language** | Java 1.1 | Java 1.1 / 1.2 / 1.3 mix | Java 22 | Rust |
| **UI** | AWT, a bare menu | AWT main window and dialogs, plus a Swing layer library | Swing, split-pane navigation | Full UI (per the v3 README) |
| **Config dialogs** | None. Ten menu items that go nowhere. | **Ten real dialogs**, each with input fields | Typed panels built per domain | See v3 |
| **Help system** | None | **A help window for every dialog** | No per-dialog help; a Help menu with About only | See v3 |
| **About box** | None | **Yes**: image layer plus scrolling-style text layer | Yes | See v3 |
| **Parameter model** | `SimSetup.~jav` (a hidden backup file!) | `SimSetup.java` promoted to the real thing | Records, enums, builder | See v3 |
| **Networking** | None | **Sockets**: demo server, demo client, Connect button | None | See v3 |
| **Sound** | None | **`AudioClip` playback of a rock sample** | None | n/a |
| **Runs a simulation?** | No | **No** - the Start button turns the window red | **Yes**, animated, with a mobility loop (per its commit log) | Yes |
| **Builds on a modern JDK?** | No (`java.applet`) | Mostly no, with exceptions (see [Can I run it?](#can-i-run-it)) | Yes | Cargo |
| **Build tool** | Borland JBuilder 2 | JBuilder 2, then JBuilder 3 | `build.sh` | Cargo |

A few relationships worth spelling out:

- **`MaRTIni-dev1/` in this repo is byte-for-byte identical to the v1 repo's contents.** `diff` finds nothing. v1 is the "before" photo, and it's kept here so the timeline is complete.
- **Everything else in this repo is not in v1.** The ten `set*` dialogs, ten `*help` windows, the `layer/` package, `AboutDialog`, `Doodle`, the Demo servers and the ipKangaroo client don't exist there.
- **v1 hid its best code in a backup file.** Its real parameter model, ported from the C simulator MADRASV3, sits in `SimSetup.~jav` while `SimSetup.java` is a 462-byte stub. In this repo `SimSetup.java` is the full 5,119-byte model. It is identical to v1's `.~jav`, so the "backup" got promoted.
- **v2's README describes itself as a modernization of v1** and only references v1. As far as I can tell, this legacy code wasn't its starting point. v2 re-implements the ten domains from scratch, with records and enums instead of `static` arrays.

---

## The timeline

Dates come from the file timestamps and the `WS_FTP.LOG` transfer logs as they were before the first commit. Git doesn't preserve modification times, so a fresh `git clone` will show you today's date on everything. The logs inside the repo keep the real story.

```
1999-08-13  First upload of MaRTIni to liman1.rutgers.edu        (WS_FTP.LOG)
1999-08-18  v1 prototype settles. dev1 is a snapshot of this.
1999-09-21  M.java and setMap.java appear: the first "real" dialog
2000-01-04  Help windows and the SimSetup/Grid help land
2000-01-24  AboutDialog.java is written
2000-01-25  The layer/ package is uploaded                        (layer/WS_FTP.LOG)
2001-02-03  Top-level M.java is saved; WS_FTP.LOG ends here
2001-02-04  dev2: the app becomes "ipKangaroo". Demo servers appear.
2001-02-05  dev3: stripped down to the client; sound + M.tar
2001-02-06  dev4: rock5.wav is bundled locally instead of fetched
```

The whole development history is about 18 months. The last five days are the weird ones.

---

## What's in the box

378 files. Roughly:

```
MaRTIni-legacy/
|
|-- M.java                  The main window (373 lines). Menus, buttons, background art.
|-- MaRTIni.java            The applet/Frame entry point from the v1 days
|-- SimSetup.java           The full parameter model (from MADRASV3's global.h)
|-- Setup.java              Same file, kept as a copy
|
|-- set*.java               TEN configuration dialogs
|     setSimSetup  setMap  setTime  setSpeed  setStatistics
|     setPower  setPropagation  setResource  setGrid  setRadioChannel
|-- *help.java              TEN matching help windows (+ a generic help.java)
|
|-- AboutDialog.java        Swing About box built on layer/
|-- layer/                  A small layered-drawing library (860 lines of Swing)
|-- Doodle.java             A paint demo, with a ColorBar
|-- Daneyand.java           A 17-line "draw my picture in a window" test
|-- LList.java, DataDrawer.java, DataOverlay.java   Helper bits
|
|-- martini.psd / martini.jpg / martinibk.jpg       The artwork
|
|-- MaRTIni-dev1/           = v1, exactly
|-- MaRTIni-dev2/           Feb 4 2001: ipKangaroo + Demo servers (the fullest snapshot)
|-- MaRTIni-dev3/           Feb 5 2001: client only, plus M.tar and ipK.zip
|-- MaRTIni-dev4/           Feb 6 2001: latest. Adds rock5.wav
|
|-- *.class                 Compiled bytecode, committed on purpose
|-- *.~jav, *.~ds, M.dsk    JBuilder backup/desktop files
|-- #SimSetuphelp.java#     Emacs autosave files. Yes, really.
|-- #ViewerDialog.java#     ...and the only trace of a ViewerDialog.java that is gone
|-- WS_FTP.LOG, ipK.bat     FTP logs; a one-line batch file ("java ipKtalk")
```

About 4,300 lines of Java sit at the top level alone.

---

## Tour of the main app: `M.java` and its ten dialogs

This is where v1's promise gets kept, mostly.

**The main window** (`M.java`) is an AWT `Frame` titled "MaRTIni". It has:

- a **File** menu: New, Open, Save, Close, Quit (Open and Save pop up real file dialogs, then do nothing with the answer);
- a **Simulation** menu with the same ten items as v1, plus **Restore Defaults** and **Clear**;
- a row of buttons: **Start**, **Set as Defaults**, **Help**, **About**, **Quit**, in two colours picked by hand (`new Color(50, 200, 255)` and `new Color(120, 160, 170)`);
- a 640x480 background image, `martinibk.jpg`, painted by a little inner class called **`PicturePerfect`**.

Each menu item opens its own dialog. Together they carry roughly **85 labelled input fields**, many of them named straight from the old C simulator:

| Dialog | Fields (a sampling) | Count |
|---|---|---|
| `setResource` | Erlang, ArrRate, CallDur, SirTarget, SirDrop, SirNew, SirHo, HysSir, HysStr, OutLimit, HoDelay ... | 18 |
| `setStatistics` | TermIndex, NoBlocked, NoArvl, NODropped, NoFinished, NoSucInterHo, NoFailIntraHo ... | 17 |
| `setGrid` | Dimension, NoBlock, DistRes, BlockSize, CellRadius, MaxCoord, TtlPoints ... | 14 |
| `setPropagation` | PropCoeff, MANHATTAN, Px, Py, N1, N2, Ny, Xb, Yb, Y0, Y1, Y2 | 12 |
| `setTime` | Simulation run Time, Transient Time, Time Resolution for Position Updates, Time Between SIR Measurements ... | 7 |
| `setRadioChannel` | BaseFile[], BaseHt, NoBase, NoChannel, NoNghbr | 5 |
| `setSpeed` | MinSpeed, MaxSpeed, MeanSpeed, SigmaSpeed, Probability Ahead | 5 |
| `setMap` | Scale, Object Scale, Base Colors, Base Lines | 4 |
| `setPower` | NoisePwr, MaxPwr | 2 |
| `setSimSetup` | one field | 1 |

Each dialog has a matching **`*help` window**: `Gridhelp`, `Maphelp`, `Powerhelp`, `Propagationhelp`, `RadioChannelhelp`, `Resourcehelp`, `SimSetuphelp`, `Speedhelp`, `Statisticshelp` and `Timehelp`.

Values are meant to be stored in ten static `Double[20]` arrays on `M` (`SimSetup1`, `Map2`, `Time3` ... `RadioChannel10`). I confirmed that `setSimSetup` parses its field into `M.SimSetup1[0]`. I didn't audit each of the other nine dialogs.

**What it doesn't do:** run a simulation. Nothing reads those arrays and does anything with them. That job moved to v2 (and then v3).

---

## The `layer/` package

An 860-line Swing mini-library for stacking drawn things on top of each other, dated Jan 25, 2000. It isn't related to the MaRTIni logic. It was uploaded alongside it and used for the About box.

| Class | Job |
|---|---|
| `Layer` | Abstract base: `draw(Graphics, Area)`, `handleClick(...)`, `configure()`, show/hide state |
| `ImageLayer` | Draws an image |
| `TextLayer` | Draws lines of text |
| `Area` | Coordinate area (270 lines, the biggest file) |
| `LayerDisplay` | A `JComponent` that composites the layers and handles mouse drag/click |
| `LayerManager` | A dialog with a list of layers, a right-click popup, and "Show / Hide" and "Configure..." |
| `Utilities` | Image loading helpers |

`AboutDialog` is the user. It stacks an `ImageLayer` (`martinibk.jpg`) under a `TextLayer` of credits and hands them to a `LayerDisplay`. Its title string is `"About datum.viewer"`, a leftover that suggests this library was lifted from another project. `DataDrawer.java` (top level) and `layer/Area.java` both refer to a `DataDisplay` type that isn't in the repo, which fits that theory. I haven't traced the origin.

The `layer/` folder is identical in the top level and in dev2, dev3 and dev4. The only difference is each copy's `WS_FTP.LOG`.

---

## Plot twist: ipKangaroo

In early February 2001 the main window **stops being MaRTIni**.

Look at the top-level `M.java` (dated Feb 3, 2001). It still has the Simulation menu, but it has also grown an **"IP Address:"** text field and **"Client"** / **"Server"** checkboxes. It's a hybrid, mid-metamorphosis.

By dev2, one day later, the transformation is done. `M.java` is 264 lines instead of 373, and:

- the window title is **"ipKangaroo"**;
- the simulation menu is gone. The File menu is just **About / Help / Exit**;
- there's an IP address field, a **Server** checkbox, and **Connect** / **Disconnect** buttons;
- a giant button across the top reads

  ```
  -  ---- < < < <  < TALK >  > > > > ----  -
  ```

  and plays a sound clip (`rock5.wav`) when pressed;
- **Connect** opens a socket to port 2222 and sends `hi\n`;
- the About box credits an "ipKangaroo java client" and a server hosted at `ipkangaroo.eyep.net` (see [Credits](#credits)).

The original `M.java` wasn't thrown away. dev2 includes it as **`Mback.java`**, and it is byte-identical to the top-level `M.java`. That is, the pre-ipKangaroo version.

The Demo files in dev2 are the scaffolding for the networking:

| File | What it is |
|---|---|
| `DemoServer.java` | A bare-bones TCP server on port 2222. Reads newline-terminated commands. Speaks `id`, `who`, `hello`, `help`, `sleep`. Replies to `id` with "My name is Harry". |
| `DemoClient.java` | Sends commands to the server and prints the responses. |
| `DemoMTServer.java` | Multi-threaded version. It imports `MTServerStuff`, which is **not in the repo**. |

The point is that the MaRTIni window was reused as a networked app's front end. A real talk client needs the same things a simulator front end does: a window, menus, a help box, an about box and a few buttons.

---

## The dev1 - dev4 snapshots

These are four manual "save as" snapshots, from before anyone put this stuff in git.

| Snapshot | Date | What it is |
|---|---|---|
| **dev1** | Aug 1999 | Identical to the v1 repo. |
| **dev2** | Feb 4, 2001 | The **fullest** one: every `set*` dialog and help window, the Demo servers, the ipKangaroo `M.java`, `Mback.java`, `ipK.zip`. |
| **dev3** | Feb 5, 2001 | A slimmed-down copy: just the ipKangaroo client, `help`, `AboutDialog`, `layer/`, art, and a 1.1 MB `M.tar` (50 entries). `M.java` loads its sound from a Rutgers URL that no longer exists. |
| **dev4** | Feb 6, 2001 | The newest. Same as dev3, plus `rock5.wav` bundled and loaded from `user.dir` so it works without the network. |

`ipK.zip` (46 files, ~640 KB uncompressed) and `M.tar` are archives of the same code and art, kept in several folders. They are the "send it to a friend" copies of the era.

---

## Archaeology notes (the funny bits)

A few things I noticed while digging. None of these are fixed. That's intentional.

- **The Start button paints the window red.** `Start` is wired to `setBackground(Color.red)`. That is the full extent of "starting the simulation."
- **"Set as Defaults" can never fire.** The button is labelled `" - Set as Defaults - "`, but the handler looks for `" - Set Defaults - "` (and would turn the window green). The strings don't match.
- **String comparison with `==`.** The handlers compare `event.getActionCommand() == " - Quit - "`. It worked because Java 1.1-era compilers interned the string literals. It's the kind of bug that works until it doesn't.
- **In ipKangaroo, "Disconnect" starts a server.** The Disconnect button's handler creates a `ServerSocket` on port 2222 and waits for a connection. It's probably the "Server" half of the app attached to the wrong button, and the code prints `"Doing the deed yo"` when it runs.
- **A hard-coded IP.** Connect goes to `128.6.201.46:2222`, a Rutgers address from 2001.
- **"My name is Harry."** That is the server's answer to `id`, and the only personality in the codebase.
- **`WINLAb dudes`.** The `Author` field in `MaRTIni.jpr`. The JBuilder project file also records the JDK as `JDK1.1.6_Borland` with Swing 1.0.1, JBCL 2.0 and JGL 3.1.0.
- **JBuilder 2 to JBuilder 3.** `M.jpr` in dev3 points at `JDK 1.3.0` and JBCL 3.0, so the toolchain was upgraded somewhere in the middle.
- **Emacs left its mark.** `#SimSetuphelp.java#` and `#ViewerDialog.java#` are autosave files from an editor that was open when something went wrong. There's no `ViewerDialog.java`, so that autosave is the only evidence it ever existed.
- **`Daneyand.java`** is 17 lines. It opens a 384x384 window and draws `martinibk.jpg` at (64, 64). Nobody remembers why.
- **`Doodle.java`** is a small paint program, including a `ColorBar` with ten colours (white, gray, red, pink, orange, yellow, green, magenta, cyan, blue). It contains File and Edit menus copied from the same template as the main app, with Undo/Cut/Copy/Paste items that have no handlers.
- **`LList.java`** is a linked list from a third party. Its header credits Terence Parr of MageLang Institute, and it declares `package collections.impl;`, so it can't compile from the top-level folder.
- **Three copies of `ipK.zip`.** And two of `M.tar`. And `martini.psd` four times. This is why the repo is bigger than it needs to be, and why that's fine.

---

## Can I run it?

I tried this on **OpenJDK 26** before writing this README.

**Works: the demo server and client.** They only use `java.io` and `java.net`.

```bash
cd MaRTIni-dev2
mkdir -p /tmp/ml && javac -d /tmp/ml DemoServer.java DemoClient.java

# terminal 1
java -cp /tmp/ml DemoServer -p 2299

# terminal 2
java -cp /tmp/ml DemoClient -p 2299 hello id help
```

Output from my run:

```
command: hello
response:
Hi there.
command: id
response:
I'm the server
My name is Harry
command: help
response:
valid commands are: id who hello help sleep
```

A 25-year-old socket server answering on a current JDK.

**Doesn't compile on JDK 26: the GUI.** `M.java`, the `set*` dialogs and the rest `import java.applet.*`. The Applet API was deprecated for removal years ago and is gone from JDK 26, so those files fail with `package java.applet does not exist`. A JDK old enough to still ship `java.applet` (JDK 8 is the obvious candidate) should be a better fit, but I haven't tried one.

Also needed at run time: `martinibk.jpg` in the working directory, since `M.java` loads it by relative path.

**Won't compile at all, as a whole:** the repo root. It mixes several generations of files, including conflicting versions of `M.java`, and `LList.java` declares a package that doesn't match its folder. When I compiled the top-level sources together (leaving out `LList.java`) I got 55 errors: 47 from the missing `java.applet` package, plus duplicate `buttons` and `SimSetup` classes and a few unresolved symbols. Compile one snapshot at a time.

---

## Honest caveats

- This README is a guided tour, not a spec. I read the sources for the points above, but I did not audit every dialog line by line.
- The timeline comes from file timestamps and FTP logs. If a file was copied or touched later, its date might not be its authoring date.
- The v2 and v3 comparisons are based on their READMEs and git logs, not a code review of those repos.
- I haven't tested the GUI on any JDK. The only runtime test I ran was the server/client demo above.
- No license file exists. `AboutDialog` shows third-party copyright notices (see below), and `LList.java` has its own author in the header. Anything reusing this should check with the original authors first.

---

## Credits

From the code itself (`AboutDialog.java` in the top level and in dev2):

**MaRTIni (WINLAB, Rutgers University)** - *Wireless Information Network Laboratory*
- Programmed by **Roland Wunderlich** and **Ramnath Ravindran**
- Repository owner and v1 author: **Daneyand Singley**
- GIF encoding: Copyright 1996, 1998 by Jef Poskanzer (jef@acme.com)
- Graphing: Copyright 1995-1999 The Regents of the University of California
- Parameter names and model: inherited from the C simulator **MADRASV3**

**ipKangaroo (2000-2001)**
- Java client developed by **Daneyand Singley** and **Vivek Bedi**
- `ipkangaroo.eyep.net` developed by **Even Lerer**, **Mary Alexander**, **Chi-Wei Yung** and **Michael Weakland**
- Special thanks to **Roland Wunderlich** for development support
- Java scroll capabilities: Copyright 1998 Roland Wunderlich

---

*See also: [MaRTIni-v1](https://github.com/007Style/MaRTIni-v1) (the "before"), [MaRTIni-v2](https://github.com/007Style/MaRTIni-v2) (the Java 22 rewrite), and v3 (the Rust one).*

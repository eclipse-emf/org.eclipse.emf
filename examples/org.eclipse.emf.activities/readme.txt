EMF Capabilities Example

This plug-in provides activity declarations for the selective hiding of EMF UI
via Eclipse's Capabilities framework. It can be used as-is when EMF is
included in a product that decides how to categorize the EMF activities and,
optionally, sets default enablement for some of them. 

This plug-in was designed to work with Galileo, an Eclipse Simultaneous
Release (http://wiki.eclipse.org/Galileo_Simultaneous_Release). In Galileo,
there is an org.eclipse.galileo plug-in that declares the relevant categories,
category bindings, and default enablement state. It uses the activities
declared in this plug-in to do so. 

The relevant declarations from Galileo also appear commented out in this
plug-in's plugin.xml file (as do the associated externalized strings in
plugin.properties), so they may be uncommented to use this plug-in outside of
Galileo.

Using this plug-in as-is without a product playing that role is not
recommended. Doing so will hide all of EMF's UI, and no explicit means of
enabling it will appear in the Eclipse Capabilities preferences (though
the Enable All button will do the trick).

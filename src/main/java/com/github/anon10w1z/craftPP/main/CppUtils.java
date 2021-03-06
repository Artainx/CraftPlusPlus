package com.github.anon10w1z.craftPP.main;

import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Contains some utility functions used by Craft++
 */
@SuppressWarnings("unchecked")
public final class CppUtils {
	/**
	 * Prevents CppUtils from being instantiated
	 */
	private CppUtils() {

	}

	/**
	 * Gets a fake player and returns it. <br>
	 * Calling this method for the first time returns a new fake player in the specified world. <br>
	 * Subsequent calls to this method will return the first fake player created.
	 *
	 * @param world The (semi-optional) world to store this fake player in
	 * @return A fake player
	 */
	public static FakePlayer getFakePlayer(World world) {
		if (world instanceof WorldServer)
			return FakePlayerFactory.getMinecraft((WorldServer) world);
		return null;
	}

	/**
	 * Finds an object by the specified name(s) in the specified object, and returns it
	 *
	 * @param object      The object to find the object in
	 * @param objectNames A list of all possible names for the object
	 * @param <T>         The data type of the object to return
	 * @return An object of the specified type with the first possible of the passed names
	 */
	public static <T> T findObject(Object object, String... objectNames) {
		try {
			Field field = ReflectionHelper.findField(object.getClass(), objectNames);
			return (T) field.get(object);
		} catch (Exception exception) {
			return null;
		}
	}

	/**
	 * Copies a list and returns the copy
	 *
	 * @param list The list to copy
	 * @param <T>  The type of the list
	 * @return A copy of the given list
	 */
	public static <T> List<T> copyList(List<T> list) {
		try {
			Constructor constructor = list.getClass().getConstructor(Collection.class);
			return (List<T>) constructor.newInstance(list);
		} catch (Exception exception) {
			return new ArrayList<T>(list);
		}
	}
}
